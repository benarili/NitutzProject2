package Controller;

import User.User;
import Vacation.Vacation;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import DataBase.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollBar;
import javafx.geometry.Orientation;
import javax.swing.event.*;
import javafx.beans.value.*;


public class ShowVacationsController {
    private Parent root;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button filter;
    public DatePicker departure_datePicker;
    private Group group=null;
    private int height=70;
    private User user;

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
        ScrollBar sc = new ScrollBar();
        sc.setLayoutX(900 - sc.getWidth());
        sc.setMin(0);
        sc.setLayoutY( 70 );
        sc.setLayoutX( 960 );
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(750);
        sc.setMax(750 );
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                group.setLayoutY(-new_val.doubleValue());
            }
        });
        if(group!=null) {
            group.getChildren().add( sc );
            Scene scene = new Scene( group );
            stage.setScene( scene );
            stage.setMaxHeight( 800 );
        }
        else{
            Scene scene=new Scene( root );
            stage.setScene( scene );
        }

    }
    public void setVacations() {
        VacationTableEntry table = new VacationTableEntry();
        ArrayList<Vacation> vacations=null;
        if(user==null)
            vacations = table.getAllAvailableVacations();
        else
            vacations=table.getAllAvailableVacations(user.getUsername());
        setVacations(vacations);
    }

    private void addVacation(Vacation v) {
        Label parameters=new Label( v.toString() );
        Button vacations = new Button("buy vacation");
        Button contact = new Button("contact seller");
        HBox hb= new HBox(  );
        hb.setSpacing( 10 );
        hb.setMargin( parameters, new Insets(20, 20, 20, 20) );
        hb.setMargin( vacations, new Insets(0, 0, 0, 0) );
        hb.setMargin( contact, new Insets(0, 0, 0, 0) );
        hb.setLayoutX( 40 );
        hb.setLayoutY( height );
        hb.setPrefWidth( 900 );
        ObservableList list = hb.getChildren();
        list.addAll(parameters,vacations,contact  );
        height+=120;
        if(group==null){
            group=new Group(root, hb );

        }
        else
            group=new Group( group,hb );
        group.getStylesheets().add("/View/MyStyle.css");

    }

    public void setStage(Stage stage, Parent root) {
        this.stage=stage;
        this.root=root;
    }

    public void setUser(User user) {
        this.user=user;
    }
}
