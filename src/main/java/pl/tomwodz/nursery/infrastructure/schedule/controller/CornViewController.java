package pl.tomwodz.nursery.infrastructure.schedule.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.infrastructure.schedule.CronGenerator;
import pl.tomwodz.nursery.domain.user.SessionData;

@Controller
@RequestMapping(path = "/view/corn")
@AllArgsConstructor
public class CornViewController {

    @Resource
    SessionData sessionData;

    private CronGenerator cronGenerator;

    @GetMapping
    public String getStatusCron(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            model.addAttribute("corn", this.cronGenerator.isRun());
            return "corn";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/run")
    public String getCornOnOff(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdmin()){
            cronGenerator.cornOnOff();
            model.addAttribute("corn", this.cronGenerator.isRun());
            return "corn";
        }
        return "redirect:/view/login";
    }
}
