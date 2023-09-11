package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.user.User;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.domain.user.UserMapper;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;
import pl.tomwodz.nursery.infrastructure.session.ModelUtils;
import pl.tomwodz.nursery.infrastructure.session.SessionData;
import pl.tomwodz.nursery.infrastructure.user.controller.error.UserNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.tomwodz.nursery.domain.user.User.Role.EMPLOYEE;

@Controller
@RequestMapping(path = "/view/user")
@AllArgsConstructor
public class UserViewController {

    @Resource
    SessionData sessionData;

    private final ChildFacade childFacade;
    private final UserFacade userFacade;
    private final GroupChildrenFacade groupChildrenFacade;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("users", this.userFacade.findAllUsers());
            return "user";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        UserResponseDto userBox = this.userFacade.findUserById(id);
        List<ChildResponseDto> childrenBox = this.childFacade.findChildrenByUserId(id);
        Map<Long, String> mapGroupChildren = new HashMap<>();
        this.groupChildrenFacade.findAllGroupsChildren().stream()
                .forEach(gch -> mapGroupChildren.put(gch.id(), gch.name()));
        model.addAttribute("user", userBox);
        model.addAttribute("children", childrenBox);
        model.addAttribute("groupChildren", mapGroupChildren);
        if (this.sessionData.isAdmin() ||
                (this.sessionData.isEmployee() && !userBox.role().equals(EMPLOYEE)) ||
                (this.sessionData.isId() == userBox.id())) {
            return "sample-user";
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
    public String postUser(@ModelAttribute User user, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        try {
            if (!this.userFacade.existsUserByLogin(user.getLogin())) {
                this.userFacade.saveUser(
                        UserMapper.mapFromUserToRequestUserDto(user));
                return "redirect:/view/login";
            } else {
                this.sessionData.setInfo("Login zajęty.");
                return "redirect:/view/user/register";
            }
        } catch (ValidationException e) {
            this.sessionData.setInfo(e.getMessage());
            return "redirect:/view/user/register";
        }
    }

    @GetMapping(path = "/update/{id}")
    public String getUserByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        UserResponseDto userBox = this.userFacade.findUserById(id);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("userModel", this.userFacade.findUserById(id));
        if (this.sessionData.isAdmin() ||
                (this.sessionData.isEmployee() && !userBox.role().equals(EMPLOYEE)) ||
                (this.sessionData.isId() == userBox.id())) {
            return "edit-user";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUserById(Model model,
                                 @ModelAttribute UpdateUserRequestDto updateUserRequestDto,
                                 @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        UserResponseDto userBox = this.userFacade.findUserById(id);
        try {
        if (this.sessionData.isAdmin() ||
                (this.sessionData.isEmployee() && !userBox.role().equals(EMPLOYEE)) ||
                (this.sessionData.isId() == userBox.id())) {
            this.userFacade.updateUser(id, updateUserRequestDto);
            model.addAttribute("userModel", this.userFacade.findUserById(id));
            this.sessionData.setInfo("Zaktualizowano użytkownika");
            return "edit-user";
        } }
        catch (ValidationException | UserNotFoundException e){
            this.sessionData.setInfo(e.getMessage());
            model.addAttribute("userModel", this.userFacade.findUserById(id));
            return "edit-user";
        }
        return "redirect:/view/login";
    }


    @GetMapping(path = "/active/{id}")
    public String changeActiveUserById(Model model,
                                 @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            this.userFacade.changeActiveUserById(id);
            return "redirect:/view/user/{id}";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            try {
                if(this.childFacade.getQuantityChildrenByUserId(id)>0){
                    model.addAttribute("message", "Nie można usunąć użytkownika, który ma dzieci.");
                    return "message";
                }
                this.userFacade.deleteUser(id);
                model.addAttribute("message", "Usunięto użytkownika o id: " + id);
                return "message";
            } catch (Exception e){
                model.addAttribute("message", "Błąd.");
                return "message";
            }
        }
        return "redirect:/view/login";
    }

}
