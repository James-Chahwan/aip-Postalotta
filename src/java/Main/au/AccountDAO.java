/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.au;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author james + labs
 */
public class AccountDAO {

    private static final String JNDI_NAME = "jdbc/aip";

    private static final String SELECT_ACCOUNT
            = "select username, password "
            + "from account ";
    private static final String ACCOUNT_ALL = SELECT_ACCOUNT;
    private static final String ACCOUNT_CREATE = "INSERT INTO ACCOUNT ( USERNAME, PASSWORD)"
            + "VALUES (?,?)";

    // basically makes a result set to return to the view for display purposes.
    private AccountDTO createRowDTO(ResultSet rs) throws SQLException {
        AccountDTO result = new AccountDTO();
        result.setUsername(rs.getString("username"));
        result.setPassword(rs.getString("password"));
        return result;
    }
    
    
    // converts the password to SHA-5 Then stores the account in the DB YAY
    public String createUser(String username, String password) throws DataStoreException, NoSuchAlgorithmException {
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(ACCOUNT_CREATE)) {
                ps.setString(1, username);
                ps.setString(2, Sha.hash256(password));

                ps.execute();

            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
        return "login";
    }
}
