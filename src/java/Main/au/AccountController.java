

package Main.au;
 // imports
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.enterprise.context.*;
import javax.faces.application.*;
import javax.faces.context.*;
import javax.inject.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import javax.validation.constraints.*;

/**
 *
 * @author james + ben + labs i guess 
 * mostly taken from the labs
 */

@Named
@RequestScoped
public class AccountController {

    @Size(min = 1)
    @Pattern(regexp = "[a-zA-Z0-9]{5,}")
    private String username;
    @Size(min = 1)
    @Pattern(regexp = "[a-zA-Z0-9]{5,}")
    private String password;

    // logs you in
    // it doesn't check if you are already logged in
    // i did play around but never got it in the end
    public String loginContainer() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {

            request.login(username, password);

            return "zermon?faces-redirect=true";

        } catch (ServletException e) {
            showError("Incorrect username or password (or you may not have properly configured aipRealm)");

            e.printStackTrace();
            return null;
        }

    }
    // logs you out
    public String logoutContainer() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.logout();
        return "login?faces-redirect=true";
    }
    // makes an account
    public String createAccount() throws DataStoreException, NoSuchAlgorithmException {
        new AccountDAO().createUser(username, password);
        return "login?faces-redirect=true";
    }

   

    // error messages
    private void showError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

    // auto gen set getters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
