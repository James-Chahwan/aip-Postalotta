/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;

import java.io.*;
import java.util.ArrayList;
import javax.validation.constraints.*;

/**
 *
 * @author james
 */
public class Post implements Serializable  {
    private int id; 
    private String title;
    private String tags;
    private String content;
    private ArrayList<Comments> ComList = new ArrayList<Comments>();

//        public Post()
//    {
//    }
//    public Post(int id, String title, String tags, String content) {
//        this.id = id;
//        this.title = title;
//        this.tags = tags;
//        this.content = content;
//    }
//    
//    public void test()
//    {
//        Post lol = new Post(1, "lol", "hashtag faggot","content sucks balls");
//        Comments comfag = new Comments(1, "jewbag", "eat a dick anon");
//        lol.ComList.add(comfag);
//        
//    }
  

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Size (min = 2)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    @Override
//    public String toString() {
//        return "Post{" + "id=" + id + ", title=" + title + ", tags=" + tags + ", content=" + content + ", ComList=" + ComList + '}';
//    }
    
    public ArrayList AllComments()
    {
        Comments comment = new Comments();
        comment.setUser("SomeGuy");
        comment.setContent("jdkfjdklsfjlkdf omgh it's so cool");
        ComList.add(comment);
        ComList.add(comment);
        ComList.add(comment);
        ComList.add(comment);
        ComList.add(comment);
       return ComList;
    }
    
    
}

