/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;
import java.sql.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.naming.*;
import javax.sql.*;
/**
 *
 * @author james
 */
public class PostDAO {
    // Configuration ------------------------------------
    
    private static final String JNDI_NAME = "jdbc/aip";
    private static final String SELECT_POSTS = "SELECT POST.POSTID, POST.TITLE, POST.CONTENT, ACCOUNT.USERNAME" +
"            FROM POST, ACCOUNT" +
"            WHERE POST.USERID = ACCOUNT.USERID";
     private static final String ALL_POSTS = SELECT_POSTS;
     private static final String USER_POSTS = SELECT_POSTS + " AND ACCOUNT.USERNAME = ?";
     private static final String CREATE_POST = "INSERT INTO POST (TITLE, CONTENT, USERID) VALUES (?, ?, ?)";
     private static final String DELETE_POST = "DELETE FROM POST WHERE POSTID = ?";
     private static final String UPDATE_POST = "UPDATE POST SET \"TITLE\" = ?, \"CONTENT\" = ?  WHERE POSTID = ?";      
     private static final String USER_POST = "SELECT TITLE, CONTENT FROM POST WHERE POSTID = ?"; // for singular
     
     private Post createRowDTO(ResultSet rs) throws SQLException
     {
             Post result = new Post();
             result.setTitle(rs.getString("title"));
             result.setContent(rs.getString("content"));
             result.setUserName(rs.getString("username"));
             result.setId(rs.getInt("postid"));
            return result;

        }
     //post is postDTO
       public ArrayList<Post> findAll() throws DataStoreException {
            ArrayList<Post> posts = new ArrayList<>();
            try
            {
                DataSource ds = InitialContext.doLookup(JNDI_NAME);
                try(Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ALL_POSTS))
                {
                    while(rs.next())
                    {
                        posts.add(createRowDTO(rs));
                    }     
                   
                }
            }
            catch (NamingException | SQLException e) {
                throw new DataStoreException(e);
            }

            return posts;
    }
       
    // find  one specific userino posterino for the post management stuff
    //   
       public ArrayList<Post> findUserPosts(String username) throws DataStoreException {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(USER_POSTS)) {

                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        posts.add(createRowDTO(rs));
                    }

                }
            } 
        }
            catch (NamingException | SQLException e) {
                throw new DataStoreException(e);
            }

        
        return posts;
    }
           
       public ArrayList<Post> findPost(int postid) throws DataStoreException {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(USER_POST)) {

                ps.setInt(1, postid);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        posts.add(createRowDTO(rs));
                    }

                }
            } 
        }
            catch (NamingException | SQLException e) {
                throw new DataStoreException(e);
            }

        
        return posts;
    }   
       
       
       
       public void newPost(String title, String content, String uname) throws DataStoreException
        {
       try{
           DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 PreparedStatement ps = conn.prepareStatement(CREATE_POST)) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, getUserid(uname));
                ps.execute();
                

            }
       } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
   }
        
    public void deletePost(int postID)throws DataStoreException
    {
        try
        {
           DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 PreparedStatement ps = conn.prepareStatement(DELETE_POST)){
                ps.setInt(1, postID);
                ps.execute();
            } 
        }
        catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
    public void editPost(String title, String content, int postID)throws DataStoreException
    {
        try
        {
           DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 PreparedStatement ps = conn.prepareStatement(UPDATE_POST)){
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, postID);
                ps.execute();
            } 
        }
        catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
        // this is just for getting the userid from the db I could always put an id in the group realm then have look up's for name
        // but i only need id's for process in crud which could be batched processed later on
        // and the lookup for id should be indexed by default...
        private int getUserid(String username)throws DataStoreException
        {  
           
            try
            {
             DataSource ds = InitialContext.doLookup(JNDI_NAME);
             try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement("select userid from account where username = ?")) 
                    {
                        ps.setString(1, username);
                         ResultSet rs = ps.executeQuery();
                                while(rs.next())
                                {
                                    return rs.getInt("USERID");
                                }
                    }
                     
            }
            catch (NamingException | SQLException e)  
            {
                 throw new DataStoreException(e);
             }
        return 0;
        }
        
}
