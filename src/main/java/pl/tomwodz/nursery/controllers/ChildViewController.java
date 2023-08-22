package pl.tomwodz.nursery.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.GroupChildrenService;
import pl.tomwodz.nursery.services.UserService;

@Controller
@RequestMapping(path="/view/child")
@AllArgsConstructor
@Log4j2
public class ChildViewController {

    private final ChildService childService;
    private final GroupChildrenService groupChildrenService;
    private final UserService userService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("children", this.childService.findAll());
        return "child";
    }

    @GetMapping(path = "/{id}")
    public String getChildById(Model model, @PathVariable Long id){
            model.addAttribute("child", this.childService.findById(id));
            return "sample-child";
    }
    @GetMapping(path = "/")
    public String getChildByCreate(Model model) {
        model.addAttribute("childModel", new Child());
        model.addAttribute("groupChildren", this.groupChildrenService.findAll());
        model.addAttribute("parents", this.userService.findAll()
                .stream()
                .filter(user -> user.getRole().equals(User.Role.PARENT))
                .toList());
        return "add-child";
    }

    @PostMapping(path = "/")
    public String postChild(@ModelAttribute Child child, Model model) {
        child.setId(0L);
        this.childService.save(child);
        model.addAttribute("message", "Dodano dziecko.");
        return "message";
    }

    @GetMapping(path = "/update/{id}")
    public String getChildByUpdate(Model model, @PathVariable Long id) {
        model.addAttribute("childModel", this.childService.findById(id));
        model.addAttribute("groupChildren", this.groupChildrenService.findAll());
        model.addAttribute("parents", this.userService.findAll()
                .stream()
                .filter(user -> user.getRole().equals(User.Role.PARENT))
                .toList());
        return "add-child";
    }

    @PostMapping(path = "/update/{id}")
    public String updateGroupChildrenById(Model model,
                                          @ModelAttribute Child child,
                                          @PathVariable Long id) {
        this.childService.updateById(id,child);
        model.addAttribute("message", "Uaktualniono dziecko.");
        return "message";
    }


}
