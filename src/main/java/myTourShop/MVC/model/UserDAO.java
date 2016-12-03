package myTourShop.MVC.model;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by imac on 01.12.16.
 */
public interface UserDAO {

    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);
    /**
     * This is the method to be used to create
     * a record in the users table.
     */
    public void create(String email, String passwordHashCode, String role);
    /**
     * This is the method to be used to list down
     * a record from the user table corresponding
     * to a passed user id.
     */
    public User getUser(String token);
    /**
     * This is the method to be used to list down
     * all the records from the user table.
     */
    public List<User> listUsers();
    /**
     * This is the method to be used to delete
     * a record from the user table corresponding
     * to a passed user id.
     */
    public void delete(int id);
    /**
     * This is the method to be used to update
     * a record into the user table.
     */
    public void update(int id, String passwordHash);

    public boolean exists(String email);

    public boolean exists(String email, String password);

    public void updateToken(String email, String token);

    public void updateEmail(String token, String email);

    public void updatePassword(String token, String password);

    public boolean existsWithToken(String token);
}
