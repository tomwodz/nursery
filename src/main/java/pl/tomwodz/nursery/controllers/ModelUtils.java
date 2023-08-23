package pl.tomwodz.nursery.controllers;

import org.springframework.ui.Model;
import pl.tomwodz.nursery.session.SessionData;

public class ModelUtils {
    public static void addCommonDataToModel(Model model, SessionData sessionData){
        model.addAttribute("logged", sessionData.isLogged());
        model.addAttribute("admin", sessionData.isAdmin());
        model.addAttribute("parent", sessionData.isParent());
        model.addAttribute("employee", sessionData.isEmployee());
    }
}