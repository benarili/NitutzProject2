package View;
import Model.*;
import Controller.*;
import DataBase.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static Stage primaryStage;
    Model model;
    Controller view;
    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        model = new Model();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
        addRegister();
        Button register=addRegister();
        Button login =addLogin();
        Group group= new Group( root,register,login );
        Scene scene = new Scene(group, 800, 700);
        scene.getStylesheets().add("/View/MyStyle.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(scene);
        view = fxmlLoader.getController();
        view.setResizeEvent(scene);
        view.setModel(model);
        view.setStage(primaryStage);
        model.addObserver(view);
        model.setController( view );
        view.setUIObjects(register,login,root);
        SetStageCloseEvent(primaryStage);
        primaryStage.show();
    }

    private Button addLogin() {
        Button login = new Button("Login");
        login.setOnAction( e -> view.login(  ) );
        login.setLayoutX(635);
        login.setLayoutY(20);
        login.setPrefWidth(150);
        return login;
    }

    private Button addRegister() {
        Button register = new Button("Register");
        register.setOnAction( e -> view.register(  ) );
        register.setLayoutX(480);
        register.setLayoutY(20);
        register.setPrefWidth(150);
        return register;

    }

    private void SetStageCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
            }
        });
    }

}