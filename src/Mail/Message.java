package Mail;

import User.User;
import DataBase.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Message {

    private boolean isRead;
    protected String text;
    private User from;
    private User to;
    private static int idCounter = 0;
    private int id;

    Message(boolean isRead, String text, User from, User to, int id) {
        this.isRead = isRead;
        this.text = text;
        this.from = from;
        this.to = to;
        this.id = id;
    }

    protected Message(User from, User to){
        this.from = from;
        this.to = to;
        this.id = idCounter;
        idCounter++;
    }
    public Message(String text, User from, User to) {
        this.isRead = false;
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    protected String getType(){
        return "chat";
    }

    protected void addToDataBase(){
        dbTableUsers db = new dbTableUsers();
        Connection connection = db.connect();
        try{
            String sql = "INSERT INTO messages " +
                    "(userFrom,userTo,messageText,messageType,messageId,isRead) " +
                    "Values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int isRead = this.isRead==true ? 1 : 0;
            preparedStatement.setString(1,from.getUsername());
            preparedStatement.setString(2,to.getUsername());
            preparedStatement.setString(3,this.getText());
            preparedStatement.setString(4,this.getType());
            preparedStatement.setInt(5,this.id);
            preparedStatement.setInt(6,isRead);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    protected String getTextToSave(){
        return getText();
    }


}
