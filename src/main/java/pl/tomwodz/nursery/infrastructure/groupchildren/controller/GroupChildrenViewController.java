package pl.tomwodz.nursery.infrastructure.groupchildren.controller;

import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.controllers.view.ModelUtils;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.session.SessionData;

import java.util.List;

@Controller
@RequestMapping(path = "/view/groupchildren")
@AllArgsConstructor
public class GroupChildrenViewController {

    @Resource
    SessionData sessionData;

    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildService childService;
    private final ChildFacade childFacade;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("groupChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("groupSize", this.childFacade.getQuantityChildrenByGroups());
            //TODO new method to facade child
            return "groupchildren";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/{id}")
    public String getGroupChildrenById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("group", this.groupChildrenFacade.findGroupChildrenById(id));
            model.addAttribute("children", this.childService.findAll()
                    .stream()
                    .filter(child -> child.getGroupChildren().getId()==id) //TODO new method to facade child
                    .toList());
            return "sample-groupchildren";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getGroupChildrenByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("groupChildrenModel", new GroupChildren());
            return "add-groupchildren";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postGroupChildren(@ModelAttribute GroupChildrenRequestDto groupChildrenRequestDto, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                this.groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);
                model.addAttribute("message", "Dodano grupę.");
                return "message";
            } catch (ValidationException e){
                this.sessionData.setInfo("Zła nazwa.");
                return "redirect:/view/groupchildren/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getGroupChildrenByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("groupChildrenModel", this.groupChildrenFacade.findGroupChildrenById(id));
            return "add-groupchildren";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateGroupChildrenById(Model model,
                                          @ModelAttribute GroupChildrenRequestDto groupChildrenRequestDto,
                                          @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                this.groupChildrenFacade.updateGroupChildren(id, groupChildrenRequestDto);
                model.addAttribute("message", "Zmieniono nazwę grupy na: " + groupChildrenRequestDto.name());
                return "message";
            } catch (ValidationException e){
                this.sessionData.setInfo("Zła nazwa.");
                return "redirect:/view/groupchildren/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteGroupChildrenById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            if (childFacade.getQuantityChildrenByGroupId(id) == 0) {
                this.groupChildrenFacade.deleteGroupChildren(id);
                model.addAttribute("message", "Usunięto grupę o id: " + id);
                return "message";
            } else {
                model.addAttribute("message", "Nie można usunąć grupy, do której przypisane są dzieci.");
                return "message";
            }
        }
        return "redirect:/view/login";
    }

}
