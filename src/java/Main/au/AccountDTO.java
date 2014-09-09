package Main.au;

//imports
import java.io.*;
import java.util.*;

/**
 *
 * @author james
 *
 * Literally for loging in this is the DTO
 */
public class AccountDTO implements Serializable {

    private String username;
    private String password;

   // non auto gen methods
    // auto gen get - setters
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
