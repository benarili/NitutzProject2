package Controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.Model.IDeletion;
import main.java.Model.Model;
import main.java.Model.ModelInit;
import main.java.Model.MyDeletionModel;

import java.util.Collection;

public class DeleteUserController {
    private ModelInit model= new Model();
    private IDeletion deletion= new MyDeletionModel();
    //DataBaseController dataBaseController;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.TextField txtfld_usr;
    public javafx.scene.control.TextField txtfld_pw;
    /*public DeleteUserController(){
        //DataBase dataBase = new DataBase("Resources/Vacation4U_DB.sqlite");
        //dataBaseController = new MyDataBaseController(dataBase);
    }*/
    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

   /* private boolean checkUsernameAndPassword(String oldUser, String oldPass) {
        String[] psswd = {"Password"};
        String[] username = {"Username"};
        String[] operations = {"="};
        String[] vals = {oldUser};

        Collection<IDataBaseEntry> user = model.querry("Users",psswd,username,operations,vals);
                //username,usernameAndPassword,operations,vals);
        boolean ans = (user != null && !user.isEmpty());
        if(ans){
            String password = "";
            for (IDataBaseEntry u:user) {
                password = u.getValue("Password");
                ans = StringChecker.stringsAreEqual(password,oldPass);
            }
        }
        return ans;
        /*
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
    }*/

    public void deleteUser(ActionEvent actionEvent) {
        String User=txtfld_usr.getText();
        String Pass=txtfld_pw.getText();
        boolean success = model.deleteUser(User, Pass);
        /*boolean exists = true;
                //checkUsernameAndPassword(User,Pass);
        if(exists){
            String[] names = {"Username", "Password"};
            String[] vals = {User,Pass};
            exists = model.delete("Users",names,vals);
        }*/
        Alert result = deletion.getResultAlert(success, "Users");
        if(success){
            txtfld_pw.clear();
            txtfld_usr.clear();
        }
        result.showAndWait();
    }
}
