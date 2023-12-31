package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

@Controller
@AllArgsConstructor
public class CommonViewController {

    @Resource
    SessionData sessionData;
    @GetMapping(path = "/")
    public String main(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "redirect:/main";
    }

    @GetMapping(path = "/main")
    public String getHomepage(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "index";
    }
}
