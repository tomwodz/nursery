package pl.tomwodz.nursery.infrastructure.presence.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.Child;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.presence.Presence;
import pl.tomwodz.nursery.domain.presence.PresenceByChildBetweenDates;
import pl.tomwodz.nursery.domain.presence.PresenceByGroupChildrenBetweenDates;
import pl.tomwodz.nursery.domain.presence.PresenceFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.domain.user.User;
import pl.tomwodz.nursery.infrastructure.session.ModelUtils;
import pl.tomwodz.nursery.infrastructure.session.SessionData;

import java.util.*;

@Controller
@RequestMapping(path = "/view/presence")
@AllArgsConstructor
public class PresenceViewController {

    @Resource
    SessionData sessionData;

    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildFacade childFacade;
    private final PresenceFacade presenceFacade;

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

    @GetMapping(path = "/")
    public String getPresenceByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", new Presence());
            model.addAttribute("children", this.childFacade.findAllChildren());
            return "add-presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getPresenceByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        PresenceResponseDto response = this.presenceFacade.findPresenceById(id);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", response);
            model.addAttribute("children", this.childFacade.findChildById(response.child_id()));
            return "edit-presence";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postPresence(Model model,
                               @ModelAttribute Presence presence) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (presence.getDataTimeEntry() == null ||
                presence.getDataTimeDeparture() == null ||
                presence.getDataTimeEntry().getDayOfYear()!=presence.getDataTimeDeparture().getDayOfYear()
        ) {
            return "redirect:/view/presence/";
        }

        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceFacade.savePresence(
                    PresenceRequestDto.builder()
                            .child_id(presence.getChild().getId())
                            .dataTimeEntry(presence.getDataTimeEntry())
                            .dataTimeDeparture(presence.getDataTimeDeparture())
                            .build());
            return "redirect:/view/presence/child/";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/child/{id}")
    public String getAllPresencesByChildId(Model model,
                                           @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        List<ChildResponseDto> children = this.childFacade.findAllChildren();
        Map<Long, String> mapChildren = new HashMap<>();
        this.childFacade.findAllChildren()
                .forEach(ch -> mapChildren.put(ch.id(), ch.name() + ' ' + ch.surname()));
        model.addAttribute("childrenMap", mapChildren);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presences", this.presenceFacade.findAllPresencesByChildId(id));
            model.addAttribute("children", children);
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(id, this.sessionData.getUser())) {
            model.addAttribute("presences", this.presenceFacade.findAllPresencesByChildId(id));
            model.addAttribute("children", this.childFacade.findChildrenByUserId(
                    this.sessionData.getUser().getId()));
            model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/{id}")
    public String getAllPresencesByGroupChildrenId(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("PresenceByGroupChildrenBetweenDates", new PresenceByGroupChildrenBetweenDates());
            model.addAttribute("presences", this.presenceFacade.findAllPresencesByGroupChildrenId(id));
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            Map<Long, String> mapChildren = new HashMap<>();
            this.childFacade.findAllChildrenByGroupChildrenId(id)
                    .forEach(ch -> mapChildren.put(ch.id(), ch.name() + ' ' + ch.surname()));
            model.addAttribute("children", mapChildren);
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
            List<PresenceResponseDto> presences =
                    this.presenceFacade
                            .findAllPresencesByGroupChildrenIdBetweenDates(
                                    request.getGroupChildren().getId(),
                                    request.getDataFrom(),
                                    request.getDataTo()
                            );
            Map<Long, String> mapChildren = new HashMap<>();
            this.childFacade.findAllChildren()
                    .forEach(ch -> mapChildren.put(ch.id(), ch.name() + ' ' + ch.surname()));
            model.addAttribute("children", mapChildren);
            model.addAttribute("presences", presences);
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("PresenceByGroupChildrenBetweenDates", new PresenceByGroupChildrenBetweenDates());
            return "presence-groupchildren";
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

    @PostMapping("/child/")
    public String getAllPresencesByChildIdBetweenDates(Model model,
                                                       @ModelAttribute PresenceByChildBetweenDates request) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("PresenceByChildBetweenDatesModel", new PresenceByChildBetweenDates());
        List<PresenceResponseDto> presences =
                this.presenceFacade
                        .findAllPresencesByChildIdBetweenDates(
                                request.getChild().getId(),
                                request.getDataFrom(),
                                request.getDataTo()
                        );
        model.addAttribute("presences", presences);
        List<ChildResponseDto> children = this.childFacade.findAllChildren();
        Map<Long, String> mapChildren = new HashMap<>();
        this.childFacade.findAllChildren()
                .forEach(ch -> mapChildren.put(ch.id(), ch.name() + ' ' + ch.surname()));
        model.addAttribute("childrenMap", mapChildren);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("children", children);
            return "presence";
        }
        if (this.sessionData.isParent() &&
                checkExistenceOfParentChildRelationship(request.getChild().getId(), this.sessionData.getUser())) {
            model.addAttribute("children", this.sessionData.getUser().getChild());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePresenceById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceFacade.deletePresence(id);
            model.addAttribute("message", "Usunięto obecność o id: " + id);
            return "message";
        }
        return "redirect:/view/presence";
    }

    @PostMapping(path = "/update/{id}")
    public String updatePresenceById(Model model,
                                     @ModelAttribute Presence presence,
                                     @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (presence.getDataTimeEntry() == null ||
                presence.getDataTimeDeparture() == null) {
            return "redirect:/view/presence/update/{id}";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            PresenceRequestDto request = PresenceRequestDto
                    .builder()
                    .child_id(presence.getChild().getId())
                    .dataTimeEntry(presence.getDataTimeEntry())
                    .dataTimeDeparture(presence.getDataTimeDeparture())
                    .build();
            this.presenceFacade.updatePresence(id, request);
            model.addAttribute("message", "Zmieniono obecność.");
            return "message";
        }
        return "redirect:/view/login";
    }

    private boolean checkExistenceOfParentChildRelationship(Long id, User user) {
        Optional<Child> childBox = user.getChild().stream().filter(child -> child.getId() == id).findFirst();
        if (childBox.isPresent()) {
            return true;
        }
        return false;
    }


}
