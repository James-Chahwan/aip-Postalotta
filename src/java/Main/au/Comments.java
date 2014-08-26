/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;

import java.io.Serializable;
import java.io.*;

/**
 *
 * @author james
 */
public class Comments implements Serializable {
    private int id;
    private String user;
    private String content; 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comments{" + "user=" + user + ", content=" + content + '}';
    }
    
    
    
}
