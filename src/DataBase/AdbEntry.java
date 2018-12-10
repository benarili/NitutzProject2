package DataBase;

import java.sql.*;
import java.util.ArrayList;

public abstract class AdbEntry {
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( "jdbc:sqlite:Nituz.db" );
        } catch (SQLException e) {
            System.out.println( e.getMessage() );
        }
        return conn;
    }
    public boolean InsertComand(String query,String[] fields ){
        boolean success = false;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
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
    public ArrayList<String> selectCommand(String query, String[] wantedColumn, String[] fields){
        String record = null;
        ArrayList<String> records=new ArrayList<>(  );
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){
            // loop through the result set
            while (rs.next()) {
                record="";
                for (int i = 0; i <wantedColumn.length ; i++) {
                    record+=rs.getString( wantedColumn[i] ) + "\t";
                }
                records.add(record);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return records;
    }

}
