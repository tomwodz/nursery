package pl.tomwodz.nursery.controllers.view;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenRepository;
import pl.tomwodz.nursery.exception.validation.ChildValidationException;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.PresenceService;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.session.SessionData;
import pl.tomwodz.nursery.validatros.ChildValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping(path = "/view/child")
@AllArgsConstructor
@Log4j2
public class ChildViewController {

    @Resource
    SessionData sessionData;

    private final ChildService childService;
    private final UserService userService;
    private final PresenceService presenceService;
    private final GroupChildrenFacade groupChildrenFacade;
    private final GroupChildrenRepository groupChildrenRepository;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("children", this.childService.findAll());
            return "child";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/{id}")
    public String getChildById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isLogged()){
            model.addAttribute("child", this.childService.findById(id));
        }
        if (this.sessionData.isAdminOrEmployee()) {
            return "sample-child";
        }
        if (this.sessionData.isParent() &&
                userService.checkExistenceOfParentChildRelationship(id, this.sessionData.getUser())) {
            return "sample-child";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getChildByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isParent()) {
            model.addAttribute("childModel", new Child());
            return "add-child";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("childModel", new Child());
            model.addAttribute("groupChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("parents", this.userService.findByRole(User.Role.PARENT));
            return "add-child";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postChild(@ModelAttribute Child child, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee() || this.sessionData.isParent()) {
            try {
                ChildValidator.validateChild(child);
                child.setId(0L);
                if (this.sessionData.isParent()) {
                    child.setParent(this.sessionData.getUser());
                    child.setGroupChildren(getGroupChildrenByNewChild());
                    Child childSaved = this.childService.save(child);
                    this.sessionData.getUser().getChild().add(childSaved);
                    model.addAttribute("message", "Dodano dziecko.");
                    return "message";
                }
                if (this.sessionData.isAdminOrEmployee()) {
                    this.childService.save(child);
                    model.addAttribute("message", "Dodano dziecko.");
                    return "message";
                }
            } catch (ChildValidationException e) {
                this.sessionData.setInfo("Dane niepoprawne.");
                return "redirect:/view/child/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getChildByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("childModel", this.childService.findById(id));
            model.addAttribute("groupChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("parents", this.userService.findByRole(User.Role.PARENT));
            return "add-child";
        }
        if (this.sessionData.isParent() &&
                userService.checkExistenceOfParentChildRelationship(id, this.sessionData.getUser())) {
            model.addAttribute("childModel", this.childService.findById(id));
            return "add-child";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateChildById(Model model,
                                  @ModelAttribute Child child,
                                  @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.isParent() &&
                        userService.checkExistenceOfParentChildRelationship(id, this.sessionData.getUser()))) {
            try {
                ChildValidator.validateChild(child);
                Child childToUpdate = this.childService.findById(id);
                childToUpdate.setName(child.getName());
                childToUpdate.setSurname(child.getSurname());
                childToUpdate.setDayOfBirth(child.getDayOfBirth());
                if(this.sessionData.isAdminOrEmployee()){
                    childToUpdate.setGroupChildren(child.getGroupChildren());
                }
                this.childService.updateById(id,childToUpdate);
                model.addAttribute("message", "Uaktualniono dziecko.");
                return "message";
            } catch (ChildValidationException e) {
                this.sessionData.setInfo("Dane niepoprawne.");
                model.addAttribute("childModel", this.childService.findById(id));
                return "redirect:/view/child/"+ id;
            }
        }
        return "redirect:/view/login";
    }


    @GetMapping(path = "/delete/{id}")
    public String deleteChildById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdminOrEmployee() ||
                this.sessionData.isParent() ||
                this.presenceService.findFirstByChildId(id)){
            model.addAttribute("message", "Nie można usunąć dziecka, które ma obecności.");
            return "message";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            this.childService.deleteById(id);
            model.addAttribute("message", "Usunięto dziecko o id: " + id);
            return "message";}
        if (this.sessionData.isParent() &&
                userService.checkExistenceOfParentChildRelationship(id, this.sessionData.getUser())) {
            this.childService.deleteById(id);
            model.addAttribute("message", "Usunięto dziecko o id: " + id);
            return "message";
        }
        return "redirect:/view/login";
    }

    public GroupChildren getGroupChildrenByNewChild(){
        Optional<GroupChildren> groupChildrenBox = this.groupChildrenRepository.findByName("Rekrutacja " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
        if(groupChildrenBox.isPresent()){
            return groupChildrenBox.get();
        } else {
            GroupChildren groupChildren = new GroupChildren("Rekrutacja " +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
            GroupChildren groupChildrenSaved = this.groupChildrenRepository.save(groupChildren);
            return groupChildrenSaved;
        }
    }


}
