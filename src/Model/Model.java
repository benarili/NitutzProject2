package Model;

import main.java.DataBase.DataBase;
import main.java.DatabaseController.DataBaseController;
import main.java.DatabaseController.MyDataBaseController;
import main.java.Entries.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.*;

public class Model extends Observable implements ModelInit {

    private DataBaseController dataBaseController = new MyDataBaseController(new DataBase("Resources/Vacation4U_DB.sqlite"));
    private ICrud users = new MyCrud(dataBaseController);

    public Model(){

    }
    /*@Override
    public boolean createTable(String[] args) {
        return false;
    }

    @Override
    public boolean update(String[] args) {
        return false;
    }

    @Override
    public boolean add(String[] args) {
        return false;
    }

    @Override
    public boolean delete(String[] args) {
        return false;
    }
*/
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
    public Alert getCreateUserMessage(boolean result, String userName) {
        Alert alert = result ? succesfullCreationOfUser(userName) : unsuccesfullCreationOfUser(userName);
        return alert;
    }

    @Override
    public boolean update(DataBaseUsable usable, String[] fieldNamesToUpdate, String[] newValues) {
        ICrud crud = getCrud(usable.getTableName());
        return crud.update(usable,fieldNamesToUpdate,newValues);
    }

    @Override
    public boolean checkCreateUserParamaters(String userName, String birthDate, String firstName, String lastName, String password) {
        //try to create user using the values. if succesfull, then values are valid, if not, they aren't
        try {
            User toCheck  = new User(userName, password,birthDate,firstName,lastName);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean insert(DataBaseUsable usable,String type) {
        //get correct crud
        ICrud crud = getCrud(type);
        return crud.add(usable);
        /*boolean result =false;
        try {
            result = dataBaseController.insert(usable);
        } catch (Exception e) {
            result =false;
        }
        return result;*/
    }

    @Override
    public boolean delete(String type, String[] fieldNames, String[] fieldValues) {
        ICrud crud = getCrud(type);
        String[] psswd = {"Password"};
        String[] username = {"UserName"};
        String[] passVal = {fieldValues[1]};
        String[] userVals = {fieldValues[0]};
        boolean exists = crud.exists("Users", username,passVal);
        if(exists){
             exists = crud.delete(type,username,userVals);
        }
        return exists;
        /*
        ICrud crud = getCrud(type);
        return crud.delete(type, fieldNames,fieldValues);
        /*String sql = "DELETE FROM "+idList.get(type)[0]+" WHERE id = "+"'"+key[0]+"'";
        return dataBaseController.execute(sql);*/
    }

    @Override
    public Collection<IDataBaseEntry> querry(String tableName, String[] fieldsToSelect, String[] fieldsWithConditions, String[] conditions, String[] conditionValues) {
        return null;
    }

    @Override
    public boolean deleteUser(String userName, String password) {
        String sql = "SELECT Password " + "FROM Users WHERE Username = '"+ userName +"'";
        if(userName.isEmpty()||password.isEmpty()) {
            return false;
        }
        else if(!password.equals(  dataBaseController.readPW( sql ) )){
            return false;
        }
        return dataBaseController.execute("DELETE FROM Users WHERE username = '" + userName + "'");
    }


    private Alert succesfullCreationOfUser(String userName){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Added Succesfully");
        alert.setContentText("User-" + userName +" added successfully");
        alert.setHeaderText("");
        return alert;
    }

    private Alert unsuccesfullCreationOfUser(String userName){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to create user");
        String username = (userName==null || userName.length()==0) ? "NULL" : userName;
        alert.setContentText("Unable to create user- "+ username);
        alert.setHeaderText("");
        return alert;
    }

    private ICrud getCrud(String type){
        ICrud crud = null;
        if(StringChecker.stringsAreEqual(type, "User")){
            crud = users;
        }
        else if(StringChecker.stringsAreEqual(type, "Users")){
            crud = users;
        }
        return crud;
    }

}
