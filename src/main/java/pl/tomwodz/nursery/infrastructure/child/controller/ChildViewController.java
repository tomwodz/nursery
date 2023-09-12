package pl.tomwodz.nursery.infrastructure.child.controller;

import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/view/child")
@AllArgsConstructor
@Log4j2
public class ChildViewController {

    @Resource
    SessionData sessionData;

    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildFacade childFacade;
    private final UserFacade userFacade;

    @GetMapping
    public String getAll(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
            Map<Long, String> mapGroupChildren = new HashMap<>();
            this.groupChildrenFacade.findAllGroupsChildren().stream()
                            .forEach(gch -> mapGroupChildren.put(gch.id(), gch.name()));
            model.addAttribute("children", this.childFacade.findAllChildren());
            model.addAttribute("groupChildren", mapGroupChildren);
            return "child";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/{id}")
    public String getChildById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isLogged()){
            ChildResponseDto childResponseDto = this.childFacade.findChildById(id);
            GroupChildrenResponseDto groupChildrenResponseDto = this.groupChildrenFacade
                    .findGroupChildrenById(childResponseDto.groupChildren_id());
            model.addAttribute("child", childResponseDto);
            model.addAttribute("groupChildren", groupChildrenResponseDto);
            model.addAttribute("user", this.userFacade.findUserByChildId(id));
        }
        if (this.sessionData.isAdminOrEmployee()) {
            return "sample-child";
        }
        if (this.sessionData.isParent()
                && this.childFacade.checkExistenceOfParentChildRelationship(id, this.sessionData.isId())) {
            return "sample-child";
        }
        return "redirect:/view/login";
    }



    @GetMapping(path = "/")
    public String getChildByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        model.addAttribute("childModel", ChildRequestDto.builder().build());
        if (this.sessionData.isParent()) {
            return "add-child";
        }
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("groupChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("parents", this.userFacade.findAllUsersByRoleParent());
            return "add-child";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postChild(@ModelAttribute ChildRequestDto childRequestDto, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee() || this.sessionData.isParent()) {
            try {
                if (this.sessionData.isParent()) {
                    ChildRequestDto childRequestDtoToSave = ChildRequestDto
                            .builder()
                            .groupChildren_id(1L)
                            .user_id(this.sessionData.getUser().getId())
                            .name(childRequestDto.name())
                            .surname(childRequestDto.surname())
                            .dayBirth(childRequestDto.dayBirth())
                            .build();
                    this.childFacade.saveChild(childRequestDtoToSave);
                    model.addAttribute("message", "Dodano dziecko.");
                    return "message";
                }
                if (this.sessionData.isAdminOrEmployee()) {
                    ChildRequestDto childRequestDtoToSaveEmployee = ChildRequestDto
                            .builder()
                            .groupChildren_id(childRequestDto.groupChildren_id())
                            .user_id(childRequestDto.user_id())
                            .name(childRequestDto.name())
                            .surname(childRequestDto.surname())
                            .dayBirth(childRequestDto.dayBirth())
                            .build();
                    this.childFacade.saveChild(childRequestDtoToSaveEmployee);
                    model.addAttribute("message", "Dodano dziecko.");
                    return "message";
                }
            } catch (ValidationException e) {
                this.sessionData.setInfo(e.getMessage());
                return "redirect:/view/child/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getChildByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("childModel", this.childFacade.findChildById(id));
            model.addAttribute("groupChildren", this.groupChildrenFacade.findAllGroupsChildren());
            model.addAttribute("parents", this.userFacade.findAllUsersByRoleParent());
            return "edit-child";
        }
        if (this.sessionData.isParent() &&
                this.childFacade.checkExistenceOfParentChildRelationship(id, this.sessionData.isId())) {
            model.addAttribute("childModel", this.childFacade.findChildById(id));
            return "edit-child";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateChildById(Model model,
                                  @ModelAttribute ChildRequestDto childRequestDto,
                                  @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.isParent() &&
                        this.childFacade.checkExistenceOfParentChildRelationship(id, this.sessionData.isId()))) {
            try {
                ChildResponseDto childResponseDto = this.childFacade.findChildById(id);
                ChildRequestDto childRequestDto1 = ChildRequestDto.builder()
                        .name(childRequestDto.name())
                        .surname(childRequestDto.surname())
                        .dayBirth(childRequestDto.dayBirth())
                        .groupChildren_id(childResponseDto.groupChildren_id())
                        .user_id(childResponseDto.user_id())
                        .build();
                this.childFacade.updateChild(id, childRequestDto1);
                if(this.sessionData.isAdminOrEmployee()){
                    ChildRequestDto childRequestDto2 = ChildRequestDto.builder()
                            .name(childRequestDto.name())
                            .surname(childRequestDto.surname())
                            .dayBirth(childRequestDto.dayBirth())
                            .groupChildren_id(childRequestDto.groupChildren_id())
                            .user_id(childRequestDto.user_id())
                            .build();
                    this.childFacade.updateChild(id, childRequestDto2);
                }
                model.addAttribute("message", "Uaktualniono dziecko.");
                return "message";
            } catch (ValidationException e) {
                this.sessionData.setInfo("Dane niepoprawne.");
                model.addAttribute("childModel", this.childFacade.findChildById(id));
                return "redirect:/view/child/"+ id;
            }
        }
        return "redirect:/view/login";
    }


  @GetMapping(path = "/delete/{id}")
    public String deleteChildById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isAdminOrEmployee() ||
                this.sessionData.isParent() &&
                        this.childFacade.checkExistenceOfParentChildRelationship(id, this.sessionData.isId())) {
            try {
                this.childFacade.deleteChildById(id);
                model.addAttribute("message", "Usunięto dziecko o id: " + id);
                return "message";
            } catch (Exception e) {
                model.addAttribute("message", "Nie można usunąć dziecka, które ma obecności.");
                return "message";
            }
        }
        return "redirect:/view/login";
    }

}
