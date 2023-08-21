package pl.tomwodz.nursery.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.services.IUserService;

@Controller
@RequestMapping(path="/view/user")
@AllArgsConstructor
public class UserViewController {

    private final IUserService userService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("users", this.userService.findAll());
        return "user";
    }

    @GetMapping(path = "/{id}")
    public String getUserById(Model model, @PathVariable Long id){
            model.addAttribute("user", this.userService.findById(id));
            return "sample-user";
    }

}
