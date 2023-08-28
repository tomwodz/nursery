package pl.tomwodz.nursery.controllers;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.GroupChildrenService;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.session.SessionData;

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
    private final GroupChildrenService groupChildrenService;
    private final UserService userService;

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
                checkExistenceOfParentChildRelationship(id).isPresent()) {
            return "sample-child";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getChildByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isParent()) {
            model.addAttribute("childModel", new Child());
            return "add-child";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("childModel", new Child());
            model.addAttribute("groupChildren", this.groupChildrenService.findAll());
            model.addAttribute("parents", this.userService.findByRole(User.Role.PARENT));
            return "add-child";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postChild(@ModelAttribute Child child, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isParent()) {
            child.setId(0L);
            child.setParent(this.sessionData.getUser());
            child.setGroupChildren(getGroupChildrenByNewChild());
            Child childSaved = this.childService.save(child);
            this.sessionData.getUser().getChild().add(childSaved);
            model.addAttribute("message", "Dodano dziecko.");
            return "message";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            child.setId(0L);
            this.childService.save(child);
            model.addAttribute("message", "Dodano dziecko.");
            return "message";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getChildByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("childModel", this.childService.findById(id));
            model.addAttribute("groupChildren", this.groupChildrenService.findAll());
            model.addAttribute("parents", this.userService.findByRole(User.Role.PARENT));
            return "add-child";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(id).isPresent()) {
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
        if (this.sessionData.isAdminOrEmployee()) {
            this.childService.updateById(id, child);
            model.addAttribute("message", "Uaktualniono dziecko.");
            return "message";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(id).isPresent()) {
            Child childToUpdate = this.childService.findById(id);
            childToUpdate.setName(child.getName());
            childToUpdate.setSurname(child.getSurname());
            childToUpdate.setDayOfBirth(child.getDayOfBirth());
            this.childService.updateById(id, childToUpdate);
            model.addAttribute("message", "Uaktualniono dziecko.");
            return "message";
        }
        return "redirect:/view/login";
    }

    private Optional<Child> checkExistenceOfParentChildRelationship(Long id){
        return this.sessionData.getUser()
                .getChild()
                .stream()
                .filter(child -> child.getId() == id)
                .findFirst();
    }

    private GroupChildren getGroupChildrenByNewChild(){
        Optional<GroupChildren> groupChildrenBox = this.groupChildrenService.findByName("Rekrutacja " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
        if(groupChildrenBox.isPresent()){
            return groupChildrenBox.get();
        } else {
            GroupChildren groupChildren = new GroupChildren("Rekrutacja " +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
            GroupChildren groupChildrenSaved = this.groupChildrenService.save(groupChildren);
            return groupChildrenSaved;
        }
    }

}
