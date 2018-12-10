package Controller;


import DataBase.DataBase;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Model.*;


import java.time.format.DateTimeFormatter;

public class CreateUserController {

    ModelInit myModel = new Model();
    //DataBaseController dataBaseController;
    private boolean created=false;

    public CreateUserController(){
        DataBase dataBase = new DataBase("Resources/Vacation4U_DB.sqlite");
        //dataBaseController = new MyDataBaseController(dataBase);
    }

    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.TextField txtfld_user_name;
    public javafx.scene.control.TextField txtfld_password;
    public javafx.scene.control.TextField txtfld_first_name;
    public javafx.scene.control.TextField txtfld_last_name;
    public javafx.scene.control.DatePicker BD;

    public void createUser(ActionEvent actionEvent) {
        //initialize all objects to be used later
        boolean success = false;
        Alert showResult = null;
        User toCreate = null;

        //get all values
        String userName=txtfld_user_name.getText();
        String password=txtfld_password.getText();
        String date=BD.getValue().format( DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String firstName = txtfld_first_name.getText();
        String lastName = txtfld_last_name.getText();
        //check values are valid
        success = myModel.checkCreateUserParamaters(userName,date,firstName,lastName,password);
        if (success) {
            try {
                toCreate = new User(userName, password, date, firstName, lastName);
                //success = dataBaseController.insert(toCreate);
                success = myModel.insert(toCreate,"User");
            } catch (Exception e) {
                success = false;
            }
        }
        showResult = myModel.getCreateUserMessage(success, userName);
        showResult.showAndWait();
        txtfld_user_name.clear();
        txtfld_first_name.clear();
        txtfld_last_name.clear();
        txtfld_password.clear();
        BD.setValue(null);


    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
