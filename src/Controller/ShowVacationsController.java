package Controller;

import User.User;
import Vacation.Vacation;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.*;
import DataBase.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ShowVacationsController {
    private Parent root;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button filter;
    public DatePicker departure_datePicker;
    private Group group=null;
    private int height=70;

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
    public void filter(ActionEvent actionEvent) {
        filter.setDisable(true);
        //set scene gilterbydatefxml



        filter.setDisable(false);
    }

    public void filterByDate(){
        java.sql.Date departure = java.sql.Date.valueOf(departure_datePicker.getValue());
        boolean result =false;
        if(!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Yakir learn GUI");
            alert.setContentText("content");
            alert.showAndWait();
            return;
        }

    }
    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setVacations(Collection<Vacation> vacations){
        for (Vacation v:vacations) {
            addVacation(v);
        }
        Scene scene=new Scene( group );
        stage.setScene( scene );
    }
    public void setVacations() {
        VacationTableEntry table = new VacationTableEntry();
        ArrayList<Vacation> vacations = table.getAllAvailableVacations();
        setVacations(vacations);
    }

    private void addVacation(Vacation v) {
        Label parameters=new Label( v.toString() );
        Button vacations = new Button("buy vacation");
        Button contact = new Button("contact seller");
        parameters.setLayoutX(40);
        parameters.setLayoutY( height );
        height+=60;
        vacations.setLayoutX( 40 );
        vacations.setLayoutY( height );
        contact.setLayoutX( 200 );
        contact.setLayoutY( height );
        height+=60;
        if(group==null){
            group=new Group(root, parameters,vacations,contact );
        }
        else
            group=new Group( group,parameters,vacations,contact );
        group.getStylesheets().add("/View/MyStyle.css");

    }

    public void setStage(Stage stage, Parent root) {
        this.stage=stage;
        this.root=root;
    }
}
