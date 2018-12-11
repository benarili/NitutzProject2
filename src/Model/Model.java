package Model;

import Controller.Controller;
import User.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements ModelInt {
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Alert getExitMessage() {
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("exit");
        exit.setHeaderText("Are you sure you want to exit?");
        ButtonType yes = new ButtonType("yes");
        ButtonType saveFirst = new ButtonType("yes, but save maze first");
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
        exit.getButtonTypes().setAll(yes,saveFirst,no);
        return exit;
    }

    @Override
    public boolean createUser(String userName, String password, String email, String date, String firstName, String lastName) {
        User newUser=new User( userName,password,email,firstName,lastName,date );
        return newUser.createNewUser();
    }

    @Override
    public User login(String userName, String password) {
        User user=new User(userName,password);
        return user;
    }

    @Override
    public void loginSucces(User user) {
        controller.isConnected=true;
        controller.user=user;
        setChanged();
        notifyObservers();

    }

}
