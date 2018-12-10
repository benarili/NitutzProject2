package Model;

import main.java.Entries.DataBaseUsable;
import main.java.Entries.IDataBaseEntry;
import javafx.scene.control.Alert;

import java.util.Collection;

public interface ModelInit {
    /*boolean createTable(String[] args);
    boolean update(String[]args);
    boolean add(String[]args);
    boolean delete(String[] args);
    */
    Alert getExitMessage();
    Alert getCreateUserMessage(boolean result, String username);
    boolean update(DataBaseUsable usable, String[] fieldNamesToUpdate, String[] newValues);
    boolean checkCreateUserParamaters(String userName, String birthDate, String firstName, String lastName, String password);
    boolean insert(DataBaseUsable usable, String type);
    boolean delete(String type, String[] fieldNames, String[] fieldValues);
    Collection<IDataBaseEntry> querry(String tableName, String[] fieldsToSelect, String[] fieldsWithConditions, String[] conditions, String[] conditionValues);
    boolean deleteUser(String userName, String password);
}
