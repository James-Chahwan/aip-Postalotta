/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;
import java.io.*;
import java.util.*;
/**
 *
 * @author james
 */
public class FakeDatabase implements Serializable {

    // Helper to generate unique identifiers
    private static int idGenerator;
    
    
    private static synchronized int generateUniqueId() {
        idGenerator++;
        return idGenerator;
    }
   
    private static LinkedHashMap<Integer, Post> posts = new LinkedHashMap<>();
   
    public static Collection<Post> findAll() {
        return posts.values();
    }
   
    public static void create(Post post) {
        post.setId(generateUniqueId());
        posts.put(post.getId(), post);
    }
   
    public static Post read(int index) {
        return posts.get(index);
    }
   
    public static void update(Post post) {
        posts.put(post.getId(), post);
    }
   
    public static void delete(int index) {
        posts.remove(index);
    }
    
}
