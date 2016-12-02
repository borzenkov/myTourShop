package myTourShop.springMVC.model;

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

    public void create(String email, String passwordHash) {
        String SQL = "insert into users (email, password_hash) values (?, ?)";
        jdbcTemplateObject.update(SQL, email, passwordHash);
        System.out.println("Created Record email = " + email + " passwordHash = " + passwordHash);
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

    public void update(int id, String passwordHash) {
        String SQL = "update users set passwordHash = ? where id = ?";
        jdbcTemplateObject.update(SQL, passwordHash, id);
        System.out.println("Updated Record with ID = " + id );
    }
}
