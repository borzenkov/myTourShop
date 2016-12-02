package myTourShop.springMVC.model;

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
    public void create(String email, String passwordHash);
    /**
     * This is the method to be used to list down
     * a record from the user table corresponding
     * to a passed user id.
     */
    public User getUser(int id);
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
}
