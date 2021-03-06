package myTourShop.MVC.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPasswordHashCode(rs.getString("password_hash_code"));
        user.setRole(rs.getString("role"));
        user.setToken(rs.getString("token"));
        return user;
    }
}