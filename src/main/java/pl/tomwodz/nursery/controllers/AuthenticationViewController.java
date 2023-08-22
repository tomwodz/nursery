package pl.tomwodz.nursery.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tomwodz.nursery.services.AuthenticationService;
import pl.tomwodz.nursery.session.SessionData;

@Controller
@RequestMapping(path="/view")
@AllArgsConstructor
public class AuthenticationViewController {

    private final AuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(Model model
    ){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("info" ,this.sessionData.getInfo());
        return "login";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password){
        try {
            this.authenticationService.authenticate(login, password);
            if(sessionData.isLogged()){
                return "redirect:/main";
            }
        }
        catch (Exception e){}
        this.sessionData.setInfo("Niepoprawny login i hasło.");
        return "redirect:/view/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        this.authenticationService.logout(request);
        return "redirect:/main";
    }

}
