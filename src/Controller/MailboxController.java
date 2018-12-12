package Controller;

import Mail.Mailbox;
import Mail.Message;
import Mail.MessageRequestToConfirm;
import User.User;
import Vacation.Vacation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MailboxController {

    private Parent root;
    private Group group=null;
    private int height=70;
    private Parent p;

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button reloadButton;
    private Mailbox mailbox;
    private String userName;

    public MailboxController(){
        //this.userName = userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void setMessages(){
        mailbox = Mailbox.recreateMailBox(new User(userName));
        setMessages(mailbox.getMessages());
    }
    public void setMessages(Collection<Message> messages){
        if(p==null && (messages==null || messages.isEmpty()))
            return;
        for (Message m:messages) {
            addMessage(m);
        }
        Scene scene;
        if(group!=null) {
            group = new Group(p, group);
            scene = new Scene(group);
        }
        else {
            group=new Group(p);
            scene = new Scene(group);
        }
        stage.setScene( scene );
    }
    public void setParent(Parent p){
        this.p=p;
    }
    private void addMessage(Message m) {
        javafx.scene.control.Label parameters=new javafx.scene.control.Label( m.getText() );
        javafx.scene.control.Button accept = new javafx.scene.control.Button("Accept Purchase");
        javafx.scene.control.Button deny = new Button("Deny Purchase");
        accept.setOnAction(e->handlePress(true, (MessageRequestToConfirm) m));
        deny.setOnAction(e->handlePress(false, (MessageRequestToConfirm) m));
        HBox hb;
        if(m instanceof MessageRequestToConfirm /*&& ((MessageRequestToConfirm) m).haveResponded()*/){
            hb= new HBox( parameters,accept,deny );
        }
        else {
            hb= new HBox( parameters);
        }
        hb.setLayoutY(height);
        height +=100;
        if(group==null){
            group=new Group( hb );
        }
        else
            group=new Group( group,hb );
        group.getStylesheets().add("/View/MyStyle.css");
    }

    private void handlePress(boolean b, MessageRequestToConfirm m) {
        m.confirm(b);
    }

    public void reload(){
        mailbox = Mailbox.recreateMailBox(new User(userName));
        this.group=null;
        setMessages(mailbox.getMessages());
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
