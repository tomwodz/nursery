package pl.tomwodz.nursery.controllers.view;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.model.Presence;
import pl.tomwodz.nursery.repository.PresenceDAO;
import pl.tomwodz.nursery.repository.dao.springdata.PresenceRepository;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.GroupChildrenService;
import pl.tomwodz.nursery.services.PresenceService;
import pl.tomwodz.nursery.session.SessionData;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping(path = "/view/presence")
@AllArgsConstructor
public class PresenceViewController {

    @Resource
    SessionData sessionData;

    private final PresenceService presenceService;
    private final ChildService childService;
    private final GroupChildrenService groupChildrenService;
    private final PresenceRepository presenceRepository;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presences", this.presenceService.findAll());
            model.addAttribute("groupChildren", this.groupChildrenService.findAll());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/{id}")
    public String getAllByGroupChildrenId(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            //model.addAttribute("presences", this.presenceRepository.findByChild_GroupChildren(id));
       model.addAttribute("presences",
                    this.presenceService.findAll().stream()
                            .filter(child -> child.getChild().getGroupChildren().getId()==id)
                            .toList()); //TODO wyciagać pofiltorwane z bazy danych*/
            model.addAttribute("groupChildren", this.groupChildrenService.findAll());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getPresenceByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel",
                    new Presence(LocalDate.now(),
                            LocalTime.of(07,00),
                            LocalTime.of(15,00)
            ));
            model.addAttribute("children", this.childService.findAll());
            return "add-presence";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postPresence(Model model,
                               @ModelAttribute Presence presence) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceService.save(presence);
            return "redirect:/view/presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getPresenceByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", this.presenceService.findById(id));
            model.addAttribute("children", this.childService.findAll());
            return "add-presence";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updatePresenceById(Model model,
                                        @ModelAttribute Presence presence,
                                        @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceService.findById(id);
            Presence newPresence = new Presence();
            newPresence.setChild(presence.getChild());
            newPresence.setDay(presence.getDay());
            newPresence.setTimeEntry(presence.getTimeEntry());
            newPresence.setTimeDeparture(presence.getTimeDeparture());
            this.presenceService.updateById(id, newPresence);
            model.addAttribute("message", "Zmieniono obecność.");
            return "message";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePresenceById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceService.deleteById(id);
            model.addAttribute("message", "Usunięto obecność o id: " + id);
            return "message";
        }
        return "redirect:/view/presence";
    }


}
