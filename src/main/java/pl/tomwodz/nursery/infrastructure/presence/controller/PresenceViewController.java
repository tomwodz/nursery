package pl.tomwodz.nursery.infrastructure.presence.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.Child;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.presence.Presence;
import pl.tomwodz.nursery.domain.presence.PresenceByChildBetweenDates;
import pl.tomwodz.nursery.domain.presence.PresenceByGroupChildrenBetweenDates;
import pl.tomwodz.nursery.domain.user.User;
import pl.tomwodz.nursery.infrastructure.ModelUtils;
import pl.tomwodz.nursery.infrastructure.session.SessionData;
import pl.tomwodz.nursery.services.PresenceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/view/presence")
@AllArgsConstructor
public class PresenceViewController {

    @Resource
    SessionData sessionData;

    private final PresenceService presenceService;
    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildFacade childFacade;



    @GetMapping(path = "/")
    public String getPresenceByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", todayPresence());
            model.addAttribute("children", this.childFacade.findAllChildren());
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
            if(presence.getDay() == null){
                presence.setDay(LocalDate.now());
            }
            this.presenceService.save(presence);
            return "redirect:/view/presence/child/";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getPresenceByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", this.presenceService.findById(id));
            model.addAttribute("children", this.childFacade.findAllChildren());
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

    @GetMapping("/child/")
    public String getModelByAllPresencesByChildIdBetweenDates(Model model,
                                                              @ModelAttribute PresenceByChildBetweenDates request) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("presences", Collections.emptyList());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("children", this.childFacade.findAllChildren());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        if (this.sessionData.isParent()) {
            model.addAttribute("children", this.sessionData.getUser().getChild());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        return "redirect:/view/login";
    }
    @PostMapping("/child/")
    public String getAllPresencesByChildIdBetweenDates(Model model,
                                                       @ModelAttribute PresenceByChildBetweenDates request) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("children", this.childFacade.findAllChildren());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            model.addAttribute("presences", this.presenceService
                    .findAllByChildIdAndDayBetween(request.getChild().getId(), request.getDataFrom(), request.getDataTo()));
            return "presence";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(request.getChild().getId(), this.sessionData.getUser())) {
            model.addAttribute("presences", this.presenceService.findAllByChildId(request.getChild().getId()));
            model.addAttribute("children", this.sessionData.getUser().getChild());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/child/{id}")
    public String getAllPresencesByChildId(Model model,
                                           @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presences", this.presenceService.findAllByChildId(id));
            model.addAttribute("children", this.childFacade.findAllChildren());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(id, this.sessionData.getUser())) {
            model.addAttribute("presences", this.presenceService.findAllByChildId(id));
            model.addAttribute("children", this.sessionData.getUser().getChild());
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/")
    public String getModelByAllPresencesByGroupChildrenIdBetweenDates(Model model,
                                                                      @ModelAttribute PresenceByGroupChildrenBetweenDates request) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presences", Collections.emptyList());
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("PresenceByGroupChildrenBetweenDates", new PresenceByGroupChildrenBetweenDates());
            return "presence-groupchildren";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/{id}")
    public String getAllByGroupChildrenId(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("PresenceByGroupChildrenBetweenDates", new PresenceByGroupChildrenBetweenDates());
            model.addAttribute("presences", this.presenceService.findAllByGroupChildrenId(id));
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            return "presence-groupchildren";
        }
        return "redirect:/view/login";
    }

    @PostMapping("/groupchildren/")
    public String getAllPresencesByGroupChildrenIdBetweenDates(Model model,
                                                               @ModelAttribute PresenceByGroupChildrenBetweenDates request) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            List<Presence> presences = this.presenceService.findAllByGroupChildrenIdAndDayBetween(
                    request.getGroupChildren().getId(),
                    request.getDataFrom(),
                    request.getDataTo());
            model.addAttribute("presences", presences);
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("PresenceByGroupChildrenBetweenDates", new PresenceByGroupChildrenBetweenDates());
            return "presence-groupchildren";
        }
        return "redirect:/view/login";
    }

    private Presence todayPresence(){
        return new Presence(LocalDate.now(),
                LocalTime.of(07,00),
                LocalTime.of(15,00));
    }

    private boolean checkExistenceOfParentChildRelationship(Long id, User user) {
        Optional<Child> childBox = user.getChild().stream().filter(child -> child.getId() == id).findFirst();
        if(childBox.isPresent()){
            return true;
        }
        return false;
    }

}
