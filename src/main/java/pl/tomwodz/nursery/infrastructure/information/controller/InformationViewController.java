package pl.tomwodz.nursery.infrastructure.information.controller;

import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.information.InformationFacade;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;

@Controller
@RequestMapping(path = "/view/information")
@AllArgsConstructor
public class InformationViewController {

    @Resource
    SessionData sessionData;

    private final InformationFacade informationFacade;
    private final UserFacade userFacade;

    @GetMapping
    public String getAllInformations(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isLogged()){
            model.addAttribute("informations", this.informationFacade.findAllInformations());
            model.addAttribute("authors", this.userFacade.findAllUsers());
            return "information";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getInformationByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("informationModel", InformationRequestDto.builder().build());
            return "add-information";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postInformation(@ModelAttribute InformationRequestDto informationRequestDto, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                InformationRequestDto informationRequestDtoToSave = InformationRequestDto.builder()
                        .author_id(this.sessionData.getUser().getId())
                        .content(informationRequestDto.content())
                        .title(informationRequestDto.title())
                        .build();
                this.informationFacade.saveInformation(informationRequestDtoToSave);
                model.addAttribute("message", "Dodano informację.");
                return "message";
            } catch (ValidationException e){
                this.sessionData.setInfo("Zła treść.");
                return "redirect:/view/information/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/update/{id}")
    public String getInformationByUpdate(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("informationModel", this.informationFacade.findInformationById(id));
            return "add-information";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateInformationById(Model model,
                                        @ModelAttribute InformationRequestDto informationRequestDto,
                                        @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                InformationRequestDto informationRequestDtoToUpdate = InformationRequestDto.builder()
                        .author_id(this.sessionData.getUser().getId())
                        .title(informationRequestDto.title())
                        .content(informationRequestDto.content())
                        .build();
                this.informationFacade.updateInformation(id, informationRequestDtoToUpdate);
                model.addAttribute("message", "Zmieniono informację.");
                return "message";
            } catch (ValidationException e){
                this.sessionData.setInfo("Zła treść.");
                return "redirect:/view/information/";
            }
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteInformationById(Model model, @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if (this.sessionData.isAdminOrEmployee()) {
                this.informationFacade.deleteInformation(id);
                model.addAttribute("message", "Usunięto informację o id: " + id);
                return "message";
        }
        return "redirect:/view/login";
    }
}
