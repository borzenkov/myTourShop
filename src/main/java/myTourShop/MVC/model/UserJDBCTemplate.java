package myTourShop.MVC.model;

import myTourShop.utils.HashCodeGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by imac on 01.12.16.
 */
public class UserJDBCTemplate implements UserDAO  {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String email, String passwordHashCode) {
        String SQL = "insert into users (email, password_hash_code) values (?, ?)";
        jdbcTemplateObject.update(SQL, email, passwordHashCode);
        System.out.println("Created Record email = " + email + " passwordHashCode = " + passwordHashCode);
    }

    public User getUser(int id) {
        String SQL = "select * from users where id = ?";
        User user = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new UserMapper());
        return user;
    }

    public List<User> listUsers() {
        String SQL = "select * from users";
        List <User> users = jdbcTemplateObject.query(SQL,
                new UserMapper());
        return users;

    }

    public void delete(int id) {
        String SQL = "delete from users where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }

    public void update(int id, String passwordHashCode) {
        String SQL = "update users set password_hash_code = ? where id = ?";
        jdbcTemplateObject.update(SQL, passwordHashCode, id);
        System.out.println("Updated Record with ID = " + id );
    }

    public boolean exists(String email) {
        String SQL = "SELECT count(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplateObject.queryForObject(SQL, Integer.class, email);
        return count != null && count > 0;
    }

    public boolean exists(String email, String password) {
        String SQL = "SELECT count(*) FROM users WHERE email = ? AND password_hash_code = ?";
        String passwordHashCode = HashCodeGenerator.getHashCode(password);
        Integer count = jdbcTemplateObject.queryForObject(SQL, Integer.class, email, passwordHashCode);
        return count != null && count > 0;
    }

    public void updateToken(String email, String token) {
        String SQL = "UPDATE users SET token = ? WHERE email = ?";
        jdbcTemplateObject.update(SQL, token, email);
    }
}
