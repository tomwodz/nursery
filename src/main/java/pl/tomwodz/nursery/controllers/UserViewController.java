package pl.tomwodz.nursery.controllers;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.dao.springdata.UserRepository;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.session.SessionData;

import java.util.EnumSet;
import java.util.Optional;

@Controller
@RequestMapping(path = "/view/user")
@AllArgsConstructor
public class UserViewController {

    @Resource
    SessionData sessionData;

    private final UserService userService;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("users", this.userService.findAll());
            return "user";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("user", this.userService.findById(id));
            return "sample-user";
        }
        if(this.sessionData.isEmployee()){
            User userBox = this.userService.findById(id);
            if(userBox.getRole().equals(User.Role.PARENT) ||
            this.sessionData.getUser().getId().equals(id)
            ) {
                model.addAttribute("user", userBox);
                return "sample-user";
            }
        }
        if(this.sessionData.isParent()){
            User userBox = this.userService.findById(id);
            if(userBox.getId()==this.sessionData.isId()) {
                model.addAttribute("user", userBox);
                return "sample-user";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/register")
    public String getUserToRegister(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("userModel", new User());
        return "register";
    }

    @PostMapping(path = "/register")
    public String postUser(@ModelAttribute User user, Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        Optional<User> userBox = this.userService.findByLogin(user.getLogin());
        if (userBox.isPresent()) {
            return "redirect:/view/user/register";
        }
        else {
            this.userService.save(user);
            return "redirect:/view/login";
        }
    }

    @GetMapping(path = "/update/{id}")
    public String getUserByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("userModel", this.userService.findById(id));
            return "edit-user";
        }
        return "redirect:/view/login";
    }
    @PostMapping(path = "/update/{id}")
    public String updateUserById(Model model,
                                  @ModelAttribute User user,
                                  @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdmin()) {
            //TODO update address
            this.userService.updateById(id,user);
            model.addAttribute("message", "Uaktualniono użytkownika.");
            return "message";
        }
        return "redirect:/view/login";
    }

}
