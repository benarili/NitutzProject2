package Model;

import User.User;
import javafx.scene.control.Alert;

public interface ModelInt {
    Alert getExitMessage();

    /**
     * create a new user
     * @param userName
     * @param password
     * @param email
     * @param date users birthdate
     * @param firstName
     * @param lastName
     * @return true if user created
     */
    boolean createUser(String userName, String password, String email, String date, String firstName, String lastName);

    /**
     * login to a user with username and password
     */
    User login(String userName, String password);

    /**
     * notify the controller about succesful login
     * @param user the logged user
     */
    public void loginSucces(User user);
}