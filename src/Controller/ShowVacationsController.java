package Controller;

import Vacation.Vacation;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.Paint.*;
import DataBase.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.effect.ColorInput;

public class ShowVacationsController {
    private Parent root;
    public javafx.scene.control.Button closeButton;
    private Group group=null;
    private int height=70;

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
    public void filter(ActionEvent actionEvent) {
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setVacations() {
        VacationTableEntry table = new VacationTableEntry();
        ArrayList<Vacation> vacations = table.getAllAvalibleVacations();
        for (Vacation v:vacations) {
            addVacation(v);
        }
        Scene scene=new Scene( group );
        stage.setScene( scene );
    }

    private void addVacation(Vacation v) {
        Label parameters=new Label( v.toString() );
        Button vacations = new Button("buy vacation");
        Button contact = new Button("contact seller");
        parameters.setLayoutX(40);
        parameters.setLayoutY( height );
        parameters.setPrefWidth( 1000 );
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
