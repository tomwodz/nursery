package pl.tomwodz.nursery.controllers;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.session.SessionData;

@Controller
@RequestMapping(path = "/view/parent")
@AllArgsConstructor
public class ParentViewController {

    @Resource
    SessionData sessionData;

    private final UserService userService;

    @GetMapping
    public String getAllParents(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdminOrEmployee()){
            model.addAttribute("users", this.userService.findByRole(User.Role.PARENT));
            return "user";
        }
        return "redirect:/view/login";
    }
}
