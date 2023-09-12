package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.user.AuthenticationFacade;
import pl.tomwodz.nursery.domain.user.ModelUtils;
import pl.tomwodz.nursery.domain.user.SessionData;


@Controller
@RequestMapping(path="/view")
@AllArgsConstructor
public class AuthenticationViewController {
    
    private final AuthenticationFacade authenticationFacade;

    @Resource
    SessionData sessionData;

    @GetMapping(path="/login")
    public String login(Model model
    ){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info" ,this.sessionData.getInfo());
        return "login";
    }

    @PostMapping(path="/login")
    public String login(@RequestParam String login, @RequestParam String password){
        try {
            this.authenticationFacade.authenticate(login, password);
            if(sessionData.isLogged()){
                return "redirect:/main";
            }
        }
        catch (ValidationException e){}
        this.sessionData.setInfo("Niepoprawny login i hasło lub konto zablokowane.");
        return "redirect:/view/login";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request){
        this.authenticationFacade.logout(request);
        return "redirect:/main";
    }





}
