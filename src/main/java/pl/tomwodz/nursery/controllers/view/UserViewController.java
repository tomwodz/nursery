package pl.tomwodz.nursery.controllers.view;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.exception.validation.AddressValidationException;
import pl.tomwodz.nursery.exception.validation.UserValidationException;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.AddressService;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.session.SessionData;
import pl.tomwodz.nursery.validatros.UserValidator;

import java.util.List;

@Controller
@RequestMapping(path = "/view/user")
@AllArgsConstructor
public class UserViewController {

    @Resource
    SessionData sessionData;

    private final UserService userService;
    private final AddressService addressService;
    private final ChildService childService;

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
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("userModel", new User());
        return "register";
    }

    @PostMapping(path = "/register")
    public String postUser(@ModelAttribute User user, Model model, @RequestParam String password2) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        try {
            UserValidator.validateUser(user);
            UserValidator.validatePasswordsEquality(user.getPassword(), password2);
            if (!this.userService.existsByLogin(user.getLogin())) {
                this.userService.save(user);
                return "redirect:/view/login";
            } else {
                this.sessionData.setInfo("Login zajęty.");
                return "redirect:/view/user/register";
            }
        } catch (UserValidationException | AddressValidationException e) {
            this.sessionData.setInfo("Dane niepoprawne.");
            return "redirect:/view/user/register";
        }
    }

    @GetMapping(path = "/update/{id}")
    public String getUserByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if(this.sessionData.isAdmin()){
            model.addAttribute("userModel", this.userService.findById(id));
            return "edit-user";
        }
        if(this.sessionData.isEmployee()){
            User userBox = this.userService.findById(id);
            if(userBox.getRole().equals(User.Role.PARENT) ||
                    this.sessionData.getUser().getId().equals(id)
            ) {
                model.addAttribute("userModel", this.userService.findById(id));
                return "edit-user";
            }
        }
        if(this.sessionData.isParent() && this.sessionData.isId() == id){
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
        model.addAttribute("info", this.sessionData.getInfo());

        if (this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.isParent() && this.sessionData.isId() == id)) {
            try {
                UserValidator.validateUserToUpdate(user);
            } catch (UserValidationException | AddressValidationException e) {
                this.sessionData.setInfo("Dane niepoprawne.");
                model.addAttribute("userModel", this.userService.findById(id));
                return "edit-user";
            }
            if (this.sessionData.isEmployee()) {
                User userBox = this.userService.findById(id);
                if (userBox.getRole().equals(User.Role.PARENT) ||
                        this.sessionData.getUser().getId().equals(id)
                ) {
                    this.updateUser(model, user, id);
                    return "message";
                }
            } else {
                this.updateUser(model, user, id);
                return "message";
            }
        }
        return "redirect:/view/login";
    }


    private String updateUser(Model model,User user, Long id){
        try {
            this.userService.updateById(id, user);
            this.addressService.updateById(id, user.getAddress());
            model.addAttribute("message", "Uaktualniono użytkownika.");
        }
        catch (Exception e){
            model.addAttribute("message", "Błąd.");

        }
        return "message";
    }

    @GetMapping(path = "/active/{id}")
    public String changeActiveUserById(Model model,
                                 @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            User userToChangeActive = this.userService.findById(id);
            this.userService.changeActiveById(id, userToChangeActive);
            model.addAttribute("users", this.userService.findByRole(userToChangeActive.getRole()));
            return "user";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            try {
                List<Child> childFromUserIdToDelete = this.userService.findById(id)
                        .getChild()
                        .stream()
                        .toList();
                childFromUserIdToDelete.stream()
                        .forEach(child -> this.childService.deleteById(child.getId()));
                this.userService.deleteById(id);
                model.addAttribute("message", "Usunięto użytkownika (i jego dzieci) o id: " + id);
                return "message";
            } catch (Exception e){
                model.addAttribute("message", "Błąd.");
                return "message";
            }
        }
        return "redirect:/view/login";
    }

}