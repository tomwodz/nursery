package pl.tomwodz.nursery.controllers.view;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.exception.validation.InformationValidationException;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.services.InformationService;
import pl.tomwodz.nursery.session.SessionData;
import pl.tomwodz.nursery.validatros.InformationValidator;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/view/information")
@AllArgsConstructor
public class InformationViewController {

    @Resource
    SessionData sessionData;

    private final InformationService informationService;

    @GetMapping
    public String getAllInformations(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        if(this.sessionData.isLogged()){
            model.addAttribute("informations", this.informationService.findAll());
            return "information";
        }
        return "redirect:/view/login";
    }

    @GetMapping(path = "/")
    public String getInformationByCreate(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            model.addAttribute("informationModel", new Information());
            return "add-information";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/")
    public String postInformation(@ModelAttribute Information information, Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                InformationValidator.validateInformation(information);
                information.setId(0L);
                information.setAuthor(this.sessionData.getUser());
                this.informationService.save(information);
                model.addAttribute("message", "Dodano informację.");
                return "message";
            } catch (InformationValidationException e){
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
            model.addAttribute("informationModel", this.informationService.findById(id));
            return "add-information";
        }
        return "redirect:/view/login";
    }

    @PostMapping(path = "/update/{id}")
    public String updateInformationById(Model model,
                                        @ModelAttribute Information information,
                                        @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info", this.sessionData.getInfo());
        if (this.sessionData.isAdminOrEmployee()) {
            try{
                InformationValidator.validateInformation(information);
                Information newInformation = new Information();
                newInformation.setAuthor(this.sessionData.getUser());
                newInformation.setDateCreation(LocalDateTime.now());
                newInformation.setContent(information.getContent());
                newInformation.setTitle(information.getTitle());
                this.informationService.updateById(id, newInformation);
                model.addAttribute("message", "Zmieniono informację.");
                return "message";
            } catch (InformationValidationException e){
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
                this.informationService.deleteById(id);
                model.addAttribute("message", "Usunięto informację o id: " + id);
                return "message";
        }
        return "redirect:/view/login";
    }
}
