package pl.tomwodz.nursery.infrastructure;

import org.springframework.ui.Model;
import pl.tomwodz.nursery.infrastructure.session.SessionData;

public class ModelUtils {
    public static void addCommonDataToModel(Model model, SessionData sessionData){
        model.addAttribute("logged", sessionData.isLogged());
        model.addAttribute("admin", sessionData.isAdmin());
        model.addAttribute("parent", sessionData.isParent());
        model.addAttribute("employee", sessionData.isEmployee());
        model.addAttribute("adminoremployee", sessionData.isAdminOrEmployee());
        model.addAttribute("getid", sessionData.isId());
    }
}
