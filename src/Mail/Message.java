package Mail;

import User.User;
import DataBase.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Message {

    private boolean isRead;
    protected String text;
    private String userNameFrom;
    private String userNameTo;
    private static int idCounter = 0;
    private int id;


    protected Message(String from, String to){
        this.userNameFrom = from;
        this.userNameTo = to;
        this.id = idCounter;
        idCounter++;
    }

    Message(){
        this.id = idCounter;
        idCounter++;
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

    public User getFromUser() {
        return new User(userNameFrom);
    }

    public String getUserNameFrom(){return userNameFrom;}

    public void setFrom(User from) {
        this.userNameFrom = from.getUsername();
    }

    public void setUserNameFrom(String userNameFrom){this.userNameFrom=userNameFrom;}

    public User getTo() {
        return new User(userNameTo);
    }

    public String getUserNameTo(){return userNameTo;}

    public void setTo(User to) {
        this.userNameTo = to.getUsername();
    }

    public void setUserNameTo(String userNameTo) {
        this.userNameTo = userNameTo;
    }

    public String getType(){
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
            preparedStatement.setString(1,userNameFrom);
            preparedStatement.setString(2,userNameTo);
            preparedStatement.setString(3,this.getText());
            preparedStatement.setString(4,this.getType());
            preparedStatement.setInt(5,this.id);
            preparedStatement.setInt(6,isRead);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String getTextToSave(){
        return getText()+"\n"+getType();
    }

    public int getId(){
        return id;
    }

   public static Message CreateMessage(int isRead, String savedText, String usernameFrom, String usernameTo, int id, String type) {
        boolean bIsRead = isRead==1 ? true : false;
        if(type.equals("chat"))
            return new Message(bIsRead,savedText,usernameFrom,usernameTo,id);
        else if(type.equals("request_confirmation"))
            return new MessageRequestToConfirm(bIsRead,savedText,usernameFrom,usernameTo,id);
        else if(type.equals("confirmation_message"))
            return new MessageConfirmedPurchase(bIsRead,savedText,usernameFrom,usernameTo,id);
        return null;
    }

    protected Message(boolean isRead, String savedText, String usernameFrom, String usernameTo, int id){
        this.isRead = isRead;
        setTextFromSavedText(savedText);
        this.userNameFrom = usernameFrom;
        this.userNameTo = usernameTo;
        this.id = id;
    }

    protected void setTextFromSavedText(String savedText){
        String[] split = savedText.split("\n");
        text = "";
        for (int i = 0; i < split.length-1; i++) {
            text+=split[i]+"\n";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Message){
            Message object = (Message)obj;
            return this.id == object.id;
        }
        return false;
    }

    public String toString(){
        return "Message "+getType()+" ,ID:"+getId()+" Text: "+getText();
    }
}
