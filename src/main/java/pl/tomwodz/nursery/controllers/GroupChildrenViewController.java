package pl.tomwodz.nursery.controllers;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.services.GroupChildrenService;
import pl.tomwodz.nursery.session.SessionData;

@Controller
@RequestMapping(path = "/view/groupchildren")
@AllArgsConstructor
public class GroupChildrenViewController {

    @Resource
    SessionData sessionData;

    private final GroupChildrenService groupChildrenService;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("groupChildren", this.groupChildrenService.findAll());
        return "groupchildren";
    }

    @GetMapping(path = "/{id}")
    public String getGroupChildrenById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("group", this.groupChildrenService.findById(id));
        return "sample-groupchildren";
    }

    @GetMapping(path = "/")
    public String getGroupChildrenByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("groupChildrenModel", new GroupChildren());
        return "add-groupchildren";
    }

    @PostMapping(path = "/")
    public String postGroupChildren(@ModelAttribute GroupChildren groupChildren, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        groupChildren.setId(0L);
        this.groupChildrenService.save(groupChildren);
        model.addAttribute("message", "Dodano grupę.");
        return "message";
    }

    @GetMapping(path = "/update/{id}")
    public String getGroupChildrenByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("groupChildrenModel", this.groupChildrenService.findById(id));
        return "add-groupchildren";
    }

    @PostMapping(path = "/update/{id}")
    public String updateGroupChildrenById(Model model,
                                          @ModelAttribute GroupChildren groupChildren,
                                          @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        GroupChildren newGroupChildren = new GroupChildren();
        newGroupChildren.setName(groupChildren.getName());
        this.groupChildrenService.updateById(id, newGroupChildren);
        model.addAttribute("message", "Zmieniono nazwę grupy na: " + groupChildren.getName());
        return "message";
    }

}
