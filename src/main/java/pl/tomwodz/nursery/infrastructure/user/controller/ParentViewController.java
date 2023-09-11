package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.infrastructure.session.ModelUtils;
import pl.tomwodz.nursery.infrastructure.session.SessionData;

@Controller
@RequestMapping(path = "/view/parent")
@AllArgsConstructor
public class ParentViewController {

    @Resource
    SessionData sessionData;

    private final UserFacade userFacade;

    @GetMapping
    public String getAllParents(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdminOrEmployee()){
            model.addAttribute("users", this.userFacade.findAllUsersByRoleParent());
            return "user";
        }
        return "redirect:/view/login";
    }
}
