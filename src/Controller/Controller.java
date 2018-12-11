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
    private Button myVacations;
    private Button addVacation;
    private Button profile;
    private Button mailbox;
    private Button showVacations;
    private Button register;
    private Button login;
    private Stage primaryStage;
    private Parent root;
    private ModelInt myModel;
    public Controller(){
        createButtons();
    }

    private void createButtons() {
        logout = new Button("Logout");
        logout.setOnAction( e -> logout(  ) );
        logout.setLayoutX(630);
        logout.setLayoutY(20);
        logout.setPrefWidth(150);
        myVacations=new Button( "my vacations" );
        myVacations.setLayoutX(280);
        myVacations.setLayoutY(330);
        myVacations.setPrefWidth(250);
        myVacations.setPrefHeight( 50 );
        addVacation=new Button( "add new vacation" );
        addVacation.setLayoutX(280);
        addVacation.setLayoutY(170);
        addVacation.setPrefWidth(250);
        addVacation.setPrefHeight( 50 );
        profile=new Button( "my profile" );
        profile.setOnAction( e -> updateUser(  ) );
        profile.setLayoutX(480);
        profile.setLayoutY(20);
    }

    private void logout() {
        isConnected=false;
        user=null;
        Group group=new Group( root,register,login,showVacations );
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
            label.setLayoutX(350);
            label.setLayoutY(20);
            Group group=new Group( root,label,logout,showVacations,addVacation,myVacations,profile );
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
    public void updateUser(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/UpdateUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            UpdateUserController update=fxmlLoader.getController();
            update.setText( user );
            update.setModel( myModel );
            Stage stage = new Stage();
            stage.initModality( Modality.APPLICATION_MODAL);
            stage.initStyle( StageStyle.UNDECORATED);
            stage.setTitle("update profile");
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
    public void showVacations() {
    }

    public void setUIObjects(Button register, Button login, Parent root,Button vacations) {
        this.register=register;
        this.login=login;
        this.root=root;
        showVacations=vacations;
    }

    public void setModel(Model model) {
        myModel=model;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }
}
