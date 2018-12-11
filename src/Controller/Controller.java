package Controller;

import Model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import User.*;
import Model.*;
public class Controller implements Observer {
    public boolean isConnected;
    public User user;
    private Button logout;
    private Button register;
    private Button login;
    private Stage primaryStage;
    private Parent root;
    private ModelInt myModel;
    public Controller(){
        logout=createLogoutButton();
    }

    private Button createLogoutButton() {
        Button logout = new Button("Logout");
        logout.setOnAction( e -> logout(  ) );
        logout.setLayoutX(630);
        logout.setLayoutY(20);
        logout.setPrefWidth(150);
        return logout;
    }

    private void logout() {
        isConnected=false;
        user=null;
        Group group=new Group( root,register,login );
        Scene scene = new Scene(group, 800, 700);
        scene.getStylesheets().add("/View/MyStyle.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setResizeEvent(Scene scene) {
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(isConnected){
            Label label = new Label("Hello " + user.getName());
            label.setLayoutX(480);
            label.setLayoutY(20);
            Group group=new Group( root,label,logout );
            Scene scene = new Scene(group, 800, 700);
            scene.getStylesheets().add("/View/MyStyle.css");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

    }
    public void login(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality( Modality.APPLICATION_MODAL);
            stage.initStyle( StageStyle.UNDECORATED);
            stage.setTitle("Login");
            LoginUserController view = fxmlLoader.getController();
            view.setModel(myModel);
            stage.setScene(new Scene(root1));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent windowEvent) {
                    windowEvent.consume();
                    stage.close();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/CreateUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality( Modality.APPLICATION_MODAL);
            stage.initStyle( StageStyle.UNDECORATED);
            stage.setTitle("Register");
            stage.setScene(new Scene(root1));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent windowEvent) {
                    windowEvent.consume();
                    stage.close();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchVacations(ActionEvent actionEvent) {
    }

    public void setUIObjects(Button register, Button login, Parent root) {
        this.register=register;
        this.login=login;
        this.root=root;
    }

    public void setModel(Model model) {
        myModel=model;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }
}
