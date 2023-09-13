package pl.tomwodz.nursery.infrastructure.presence.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.presence.PresenceFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                              @ModelAttribute PresenceRequestDto requestDto) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("presences", Collections.emptyList());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("children", this.childFacade.findAllChildren());
            model.addAttribute("PresenceByChildBetweenDatesModel", PresenceRequestDto.builder().build());
            return "presence";
        }
        if (this.sessionData.isParent()) {
            model.addAttribute("children", this.childFacade.findChildrenByUserId(this.sessionData.isId()));
            model.addAttribute("PresenceByChildBetweenDatesModel", PresenceRequestDto.builder().build());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getPresenceByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presenceModel", PresenceRequestDto.builder().build());
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
                               @ModelAttribute PresenceRequestDto requestDto) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (requestDto.dataTimeEntry() == null ||
                requestDto.dataTimeDeparture() == null ||
                requestDto.dataTimeEntry().getDayOfYear()!=requestDto.dataTimeDeparture().getDayOfYear()
        ) {
            return "redirect:/view/presence/";
        }

        if (this.sessionData.isAdminOrEmployee()) {
            this.presenceFacade.savePresence(/*
                    PresenceRequestDto.builder()
                            .id(requestDto.id())
                            .dataTimeEntry(requestDto.dataTimeEntry())
                            .dataTimeDeparture(requestDto.dataTimeDeparture())
                            .build()*/requestDto);
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
            model.addAttribute("PresenceByChildBetweenDatesModel", PresenceRequestDto.builder().build());
            return "presence";
        }
        if (this.sessionData.isParent() &&
                this.childFacade.checkExistenceOfParentChildRelationship(id, this.sessionData.isId())) {
            model.addAttribute("presences", this.presenceFacade.findAllPresencesByChildId(id));
            model.addAttribute("children", this.childFacade.findChildrenByUserId(
                    this.sessionData.getUser().getId()));
            model.addAttribute("PresenceByChildBetweenDatesModel", PresenceRequestDto.builder().build());
            return "presence";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/{id}")
    public String getAllPresencesByGroupChildrenId(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("PresenceByGroupChildrenBetweenDates", PresenceRequestDto.builder().build());
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
                                                               @ModelAttribute PresenceRequestDto requestDto) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            List<PresenceResponseDto> presences =
                    this.presenceFacade
                            .findAllPresencesByGroupChildrenIdBetweenDates(
                                    requestDto.id(),
                                    requestDto.dataTimeEntry(),
                                    requestDto.dataTimeDeparture()
                            );
            Map<Long, String> mapChildren = new HashMap<>();
            this.childFacade.findAllChildren()
                    .forEach(ch -> mapChildren.put(ch.id(), ch.name() + ' ' + ch.surname()));
            model.addAttribute("children", mapChildren);
            model.addAttribute("presences", presences);
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("PresenceByGroupChildrenBetweenDates", PresenceRequestDto.builder().build());
            return "presence-groupchildren";
        }
        return "redirect:/view/login";
    }

    @GetMapping("/groupchildren/")
    public String getModelByAllPresencesByGroupChildrenIdBetweenDates(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("presences", Collections.emptyList());
            model.addAttribute("groupsChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("PresenceByGroupChildrenBetweenDates", PresenceRequestDto.builder().build());
            return "presence-groupchildren";
        }
        return "redirect:/view/login";
    }

    @PostMapping("/child/")
    public String getAllPresencesByChildIdBetweenDates(Model model,
                                                       @ModelAttribute PresenceRequestDto requestDto) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("PresenceByChildBetweenDatesModel", PresenceRequestDto.builder().build());
        List<PresenceResponseDto> presences =
                this.presenceFacade
                        .findAllPresencesByChildIdBetweenDates(
                                requestDto.id(),
                                requestDto.dataTimeEntry(),
                                requestDto.dataTimeDeparture()
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
        if (this.sessionData.isParent()) {
            model.addAttribute("children",  this.childFacade.findChildrenByUserId(this.sessionData.isId()));
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
                                     @ModelAttribute PresenceRequestDto requestDto,
                                     @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (requestDto.dataTimeEntry() == null ||
                requestDto.dataTimeDeparture() == null) {
            return "redirect:/view/presence/update/{id}";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            PresenceRequestDto request = PresenceRequestDto
                    .builder()
                    .id(requestDto.id())
                    .dataTimeEntry(requestDto.dataTimeEntry())
                    .dataTimeDeparture(requestDto.dataTimeDeparture())
                    .build();
            this.presenceFacade.updatePresence(id, request);
            model.addAttribute("message", "Zmieniono obecność.");
            return "message";
        }
        return "redirect:/view/login";
    }

}
