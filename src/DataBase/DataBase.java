package DataBase;
import java.sql.*;

public class DataBase implements IRelationalDB {
    /**
     * a sample database
     */
    private String location;
    private String fileName;
    private Connection conn;


    private static DataBase instance;

    public void insert(AdbEntry entry){
        if(entry instanceof )
    }
    public static DataBase getInstance(){
        if (instance==null)
            instance = new DataBase("projectDB");
        return instance;
    }

    private DataBase(String fileName) {
        this.fileName=fileName;
        this.conn = null;
        location = "jdbc:sqlite:" + fileName;
    }

    protected Connection connect() {

        conn = null;
        try {
            // db parameters
            //String url = "jdbc:sqlite:nituzDB.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(location);
            /*String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                    + "	user_name text PRIMARY KEY,\n"
                    + "	password text NOT NULL,\n"
                    + "	name text NOT NULL,\n"
                    + "	last_name text NOT NULL,\n"
                    + "	birth_date text NOT NULL,\n"
                    + "	capacity real\n"
                    + ");";*/
            /*if (conn != null) {

                System.out.println("A new database, " + fileName + ", has been connected to.");
            }

            System.out.println("Connection to " + fileName + " has been established.");*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    protected void disConnect() {
        try {
            if (conn != null) {
                conn.close();
                this.conn = null;
                System.out.print("Disconmected from " + fileName );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Create a new table in the test database
     *
     */
    public boolean executeInsertCommand(String[] fields,String toExecute){
        boolean success = false;
        connect();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(toExecute)) {
            for (int i = 1; i <=fields.length ; i++) {
                pstmt.setString(i, fields[i-1]);
            }
            pstmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    @Override
    public String executeSelectCommand(String sql) {
        String record = null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                record=rs.getString("Username") +  "\t" +
                        rs.getString("Password") + "\t" +
                        rs.getString("Birth_Date") + "\t" +
                        rs.getString("First_Name") + "\t" +
                        rs.getString("Last_Name");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return record;
    }
    public String executeSelectPWCommand(String sql) {
        String record = null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            record=rs.getString("Password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return record;
    }

    @Override
    public ResultSet querry(String sql) {
        Connection conn = connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean executeSQLCommand(String toExecute)
    {
        if (!isValidInput(toExecute)) {
            System.out.println("invalid command");
            return false;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(toExecute)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     *
     * @param toCheck input to check
     * @return
     */
    private boolean isValidInput(String toCheck){
        return toCheck != null && toCheck != "";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataBase db = new DataBase("Resources/Vacation4U_DB.sqlite");
        db.connect();
        db.disConnect();
    }
}
