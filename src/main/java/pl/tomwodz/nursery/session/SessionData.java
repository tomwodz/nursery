package pl.tomwodz.nursery.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.tomwodz.nursery.model.User;


@NoArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class SessionData {

    private User user = null;
    private String info = null;
    
    public boolean isLogged() {
        return this.user != null && this.user.isActive();
    }

    public boolean isAdmin() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.ADMIN;
    }

    public boolean isEmployee() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.EMPLOYEE && this.user.isActive();
    }

    public boolean isAdminOrEmployee() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.ADMIN
                || (this.user.getRole() == User.Role.EMPLOYEE && this.user.isActive());
    }

    public boolean isParent() {
        if (this.user == null) {
            return false;
        }
        return this.user.getRole() == User.Role.PARENT && this.user.isActive();
    }


    public String getInfo() {
        if (this.info == null) {
            return "";
        } else {
            String temp = this.info;
            this.info = null;
            return temp;
        }
    }

    public long isId() {
        if (this.user == null) {
            return 0;
        }
        return this.user.getId();
    }


}
