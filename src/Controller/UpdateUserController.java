package Controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.DataBase.DataBase;
import main.java.DatabaseController.DataBaseController;
import main.java.DatabaseController.MyDataBaseController;

import java.time.format.DateTimeFormatter;


public class UpdateUserController {
    DataBaseController dataBaseController;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.TextField txtfld_oldusr;
    public javafx.scene.control.TextField txtfld_oldpw;
    public javafx.scene.control.TextField txtfld_newpw;
    public javafx.scene.control.TextField txtfld_newfn;
    public javafx.scene.control.TextField txtfld_newln;
    public javafx.scene.control.DatePicker new_BD;
    public UpdateUserController(){
        DataBase dataBase = new DataBase("Resources/Vacation4U_DB.sqlite");
        dataBaseController = new MyDataBaseController(dataBase);
    }
    public void updateUser(ActionEvent actionEvent) {
        String oldUser=txtfld_oldusr.getText();
        String oldPass=txtfld_oldpw.getText();
        checkUsernameAndPassword(oldUser,oldPass);
        String newPass=txtfld_newpw.getText();
        String newFirstName=txtfld_newfn.getText();
        String newLastName=txtfld_newln.getText();
        String newBD="";
        if(new_BD.getValue()!=null)
            newBD=new_BD.getValue().format( DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(checkEmptyFields(newPass,newFirstName,newLastName,newBD)){
            checkRecordsToUpdate(oldUser,newPass,newFirstName,newLastName,newBD);
        }
    }

    private void checkRecordsToUpdate(String oldUser,String newPass, String newFirstName, String newLastName, String newBD) {
        if(!newPass.isEmpty())
            updateRecord(oldUser,newPass,"Password");
        if(!newFirstName.isEmpty())
            updateRecord(oldUser,newFirstName,"First_Name");
        if(!newLastName.isEmpty())
            updateRecord(oldUser,newLastName,"Last_Name");
        if(!newBD.isEmpty())
            updateRecord(oldUser,newBD,"Birth_Date");
        Alert result=new Alert( Alert.AlertType.INFORMATION );
        result.setHeaderText( "Update complete" );
        result.showAndWait();

    }

    private void updateRecord(String oldUser, String newRecord, String column) {
        String sql="UPDATE Users SET " + column + "=" +"'" +newRecord + "'" +" WHERE Username = '" +oldUser + "'";
        dataBaseController.update(sql);
    }

    private boolean checkEmptyFields(String newPass, String newFirstName, String newLastName, String newBD) {
        if(newPass.isEmpty()&&newFirstName.isEmpty()&&newLastName.isEmpty()&&newBD.isEmpty()){
            Alert result = new Alert( Alert.AlertType.WARNING );
            result.setHeaderText( "Nothing to update" );
            result.showAndWait();
            txtfld_oldusr.setText("");
            txtfld_oldpw.setText("");
            return false;
        }
        return true;
    }

    private void checkUsernameAndPassword(String oldUser, String oldPass) {
        Alert result;
        String sql = "SELECT Password " + "FROM Users WHERE Username = '"+ oldUser +"'";
        if(oldUser.isEmpty()||oldPass.isEmpty()) {
            result = new Alert( Alert.AlertType.WARNING );
            result.setHeaderText( "Empty username or password!" );
            result.showAndWait();
        }
        else if(!oldPass.equals(  dataBaseController.readPW( sql ) )){
            result = new Alert( Alert.AlertType.WARNING );
            result.setHeaderText( "Wrong username or password!" );
            result.showAndWait();
        }
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
