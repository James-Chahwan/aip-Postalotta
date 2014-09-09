/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.au;

/**
 *
 * @author james
 */
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
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class PostController {
    // validation and strings stuff
    @Size(min = 1, message = "please enter a title, meow")
    private String title;
    @Size(min = 1, message = "please enter some content ")
    private String content;
    // makes alot of things way easier and less intensive instead of doing lookups for each row.....
    private String UserName;
    private int likes;

    // get all the posts for zermon
    public ArrayList<Post> getAllPosts() throws DataStoreException {

        return new PostDAO().findAll();
    }
    // get all the posts for user management page
    public ArrayList<Post> getUserPosts() throws DataStoreException {
        FacesContext context = FacesContext.getCurrentInstance();
        String uname = context.getExternalContext().getUserPrincipal().getName();
        return new PostDAO().findUserPosts(uname);
    }
    // making a post
    public String createPost() throws DataStoreException {
        FacesContext context = FacesContext.getCurrentInstance();
        String uname = context.getExternalContext().getUserPrincipal().getName();
        new PostDAO().newPost(title, content, uname);
        return "zermon?faces-redirect=true";
    }
    // it calls the delete method in the dao 
    //no controller CAN EVER DO ANY DB WORK
    public void deletePost(int postid) throws DataStoreException {
        new PostDAO().deletePost(postid);
    }
    // increment that like 
    // uncapped so users can feel better about themselves :D
    public void likePost(int likeys, int postid) throws DataStoreException {
        ++likeys;
        new PostDAO().likeyPost(likeys, postid);
    }
    // debugging method 
    // if sun is missing it won't call
    // if i didn't put a form 
    // etc if nothing can call this it's a jsf page issue.
    public String meh() {
        System.out.println("this works");
        return null;
    }

//public void editPost(int postid) throws DataStoreException
//{
//    new PostDAO().editPost(title, content, postid);
//}
//   public Post getPost(){
//       return post;
//   }
//   
//   
//   public void loadPost(int index){
//       System.out.println(index + "Posts COntroller  INDEX");
//       post = FakeDatabase.read(index);
//   }
//   public String saveAsNew()
//   {
//       FakeDatabase.create(post);
//       return "zermon?faces-redirect=true";
//   }
//        
//   public String delete()
//   {
//       System.out.println("post controller DELETE ");
//       FakeDatabase.delete(post.getId());
//       return "zermon?faces-redirect=true";
//   }
//   public String update()
//   {
//       FakeDatabase.update(post);
//       return "zermon?faces-redirect=true";
//   }
    // getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

}
