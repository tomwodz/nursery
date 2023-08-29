package pl.tomwodz.nursery.controllers.view;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.exception.validation.UserValidationException;
import pl.tomwodz.nursery.services.AuthenticationService;
import pl.tomwodz.nursery.session.SessionData;
import pl.tomwodz.nursery.validatros.UserValidator;

@Controller
@RequestMapping(path="/view")
@AllArgsConstructor
public class AuthenticationViewController {

    private final AuthenticationService authenticationService;

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
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
            this.authenticationService.authenticate(login, password);
            if(sessionData.isLogged()){
                return "redirect:/main";
            }
        }
        catch (UserValidationException e){}
        this.sessionData.setInfo("Niepoprawny login i hasło lub konto zablokowane.");
        return "redirect:/view/login";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request){
        this.authenticationService.logout(request);
        return "redirect:/main";
    }





}
