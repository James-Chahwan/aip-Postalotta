

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

/**
 *
 * @author james
 */

@Named
@RequestScoped
public class AccountController {
   private String username;
   private String password;
   
   
   
    public String loginContainer() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
        try {
            System.out.println("we in the login container");
          request.login(username, password);
          
          return "zermon?faces-redirect=true";
          
        } catch (ServletException e) {
          showError("Incorrect username or password (or you may not have properly configured aipRealm)");
          
          e.printStackTrace();
          return null;
        }
      
    }
   
     public String logoutContainer() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        request.logout();
        return "login?faces-redirect=true";
    }  
   public String createAccount() throws DataStoreException, NoSuchAlgorithmException
   {
       new AccountDAO().createUser(username, password);
       return "login?faces-redirect=true";
   }
   public String something()
   {
       System.out.println("something here check");
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
