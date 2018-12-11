package User;
import DataBase.*;

import java.util.ArrayList;

public class User {
    private String username;
    private String pass;
    private String email;
    private String name;
    private String lastname;
    private String birthDath;
    private dbTableUsers db=new dbTableUsers();

    public User(String username, String pass, String email, String name, String lastname, String birthDath) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.birthDath = birthDath;
    }
    public boolean createNewUser(){
        String[] insert={username,pass,email,name,lastname,birthDath};
        return db.InsertComand( "INSERT INTO users(username,password,email,name,last_name,birth_date,image) VALUES(?,?,?,?,?,?,?)",insert );
    }
    public ArrayList<String> selectUser(String query, String[] fields){
        return db.selectCommand( query,fields );
    }
}
