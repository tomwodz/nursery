package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

@Controller
@RequestMapping(path = "/view/employee")
@AllArgsConstructor
public class EmployeeViewController {

    @Resource
    SessionData sessionData;

    private final UserFacade userFacade;

    @GetMapping
    public String getAllParents(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdminOrEmployee()){
            model.addAttribute("users", this.userFacade.findAllUsersByRoleEmployee());
            return "user";
        }
        return "redirect:/view/login";
    }
}
