package Model;

import javafx.scene.control.Alert;

public interface IDeletion {
    public Alert getResultAlert(boolean result, String type);
    public Alert getConfirmation(String type);
}
