package com.example.login;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class UserService {

    public boolean validateUser(String username, String password) {
        boolean isValid = false;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/db2DS");

            try (Connection conn = ds.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT COUNT(*) FROM users WHERE username=? AND password=?"
                ); ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    isValid = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
