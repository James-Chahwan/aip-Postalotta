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

import java.io.*;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class PostController implements Serializable {

    private Post post = new Post();

   public Post getPost()
   {
       return post;
   }
   public void loadPost(int index){
       post=FakeDatabase.read(index);
   }
   public String saveAsNew()
   {
       FakeDatabase.create(post);
       return "zermon?faces-redirect=true";
   }
        
   public String delete()
   {
       FakeDatabase.delete(post.getId());
       return "zermon?faces-redirect=true";
   }
   public String update()
   {
       FakeDatabase.update(post);
       return "zermon?faces-redirect=true";
   }
}
