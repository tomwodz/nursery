package pl.tomwodz.nursery.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.services.GroupChildrenService;

@Controller
@RequestMapping(path="/view/groupchildren")
@AllArgsConstructor
public class GroupChildrenViewController {

    private final GroupChildrenService groupChildrenService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("groupChildren", this.groupChildrenService.findAll());
        return "groupchildren";
    }

    @GetMapping(path = "/{id}")
    public String getGroupChildrenById(Model model, @PathVariable Long id) {
        model.addAttribute("group", this.groupChildrenService.findById(id));
        return "sample-groupchildren";
    }

    @GetMapping(path = "/")
    public String getGroupChildrenByCreate(Model model) {
        model.addAttribute("groupChildrenModel", new GroupChildren());
        return "add-groupchildren";
    }

    @PostMapping(path = "/")
    public String postGroupChildren(@ModelAttribute GroupChildren groupChildren, Model model) {
        groupChildren.setId(0L);
        this.groupChildrenService.save(groupChildren);
        model.addAttribute("message", "Dodano grupę.");
        return "message";
    }

    @GetMapping(path = "/update/{id}")
    public String getGroupChildrenByUpdate(Model model, @PathVariable Long id) {
            model.addAttribute("groupChildrenModel", this.groupChildrenService.findById(id));
            return "add-groupchildren";
    }

    @PostMapping(path = "/update/{id}")
    public String updateGroupChildrenById(Model model,
                                       @ModelAttribute GroupChildren groupChildren,
                                       @PathVariable Long id) {
        GroupChildren newGroupChildren = new GroupChildren();
        newGroupChildren.setName(groupChildren.getName());
        this.groupChildrenService.updateById(id, newGroupChildren);
        model.addAttribute("message", "Zmieniono nazwę grupy na: " + groupChildren.getName());
        return "message";
    }

}
