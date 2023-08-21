package pl.tomwodz.nursery.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.services.IChildService;

@Controller
@RequestMapping(path="/view/child")
@AllArgsConstructor
public class ChildViewController {

    private final IChildService childService;

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

}
