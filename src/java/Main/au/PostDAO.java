/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
/**
 *
 * @author james
 */
public class PostDAO {
    // Configuration ------------------------------------
    
    private static final String JNDI_NAME = "jdbc/aip";
    private static final String SELECT_POSTS = "SELECT POST.TITLE, POST.CONTENT, ACCOUNT.USERNAME" +
"            FROM POST, ACCOUNT" +
"            WHERE POST.USERID = ACCOUNT.USERID";
     private static final String ALL_POSTS = SELECT_POSTS;
     private static final String USER_POSTS = SELECT_POSTS + " AND WHERE ACCOUNT.USERNAME = ?";
     
     
     private Post createRowDTO(ResultSet rs) throws SQLException
     {
             Post result = new Post();
             result.setTitle(rs.getString("title"));
             result.setContent(rs.getString("content"));
             result.setUserName(rs.getString("username"));
            return result;

        }
     //post is postDTO
       public ArrayList<Post> findAll() throws DataStoreException {
           System.out.println("this find all has been called ");
            ArrayList<Post> posts = new ArrayList<>();
            try
            {
                DataSource ds = InitialContext.doLookup(JNDI_NAME);
                try(Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ALL_POSTS))
                {
                    System.out.println("before the loop");
                    System.out.println(rs.next()== true);
                    while(rs.next())
                    {
                        posts.add(createRowDTO(rs));
                        System.out.println("inside the loop");
                        System.out.println(posts.toString());
                    }     
                   
                }
            }
            catch (NamingException | SQLException e) {
                throw new DataStoreException(e);
            }

            return posts;
    }
}
