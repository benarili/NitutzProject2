package Model;

import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class MyDeletionModel implements IDeletion {

    private Map<String,String> unsuccesful_headers;
    private Map<String,String> unsuccesful_content;
    private Map<String,String> succesfull_headers;
    private Map<String,String> succesfull_content;


    public MyDeletionModel(){
        unsuccesful_headers = new HashMap<>();
        unsuccesful_content = new HashMap<>();
        succesfull_content = new HashMap<>();
        succesfull_headers = new HashMap<>();
        unsuccesful_content.put("Users", "cannot delete said user with given password");
        unsuccesful_headers.put("Users", "deletion unsuccessful");
        succesfull_headers.put("Users", "Deletion Successful");
        succesfull_content.put("Users", "Succesfully deleted user");
    }
    @Override
    public Alert getResultAlert(boolean result, String type) {
        return result ? getSuccesfullAlert(type) : getUnsuccesfullAlert(type);
    }

    private Alert getSuccesfullAlert(String type){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(succesfull_headers.get(type));
        alert.setContentText(succesfull_content.get(type));
        alert.setTitle("");
        return alert;
    }

    private Alert getUnsuccesfullAlert(String type){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(unsuccesful_headers.get(type));
        alert.setContentText(unsuccesful_content.get(type));
        alert.setTitle("");
        return alert;
    }

    public Alert getConfirmation(String type){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deltion");
        alert.setContentText("Are you sure tat you want to delete "+type);
        alert.setTitle("");
        return alert;
    }


}
