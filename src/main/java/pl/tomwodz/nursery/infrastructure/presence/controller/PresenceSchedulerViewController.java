package pl.tomwodz.nursery.infrastructure.presence.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

@Controller
@RequestMapping(path = "/view/corn")
@AllArgsConstructor
public class PresenceSchedulerViewController {

    @Resource
    SessionData sessionData;

    private PresenceScheduler presenceScheduler;

    @GetMapping
    public String getStatusCron(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("corn", this.presenceScheduler.isRun());
            return "corn";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/run")
    public String getCornOnOff(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            presenceScheduler.cornOnOff();
            model.addAttribute("corn", this.presenceScheduler.isRun());
            return "corn";
        }
        return "redirect:/view/login";
    }
}
