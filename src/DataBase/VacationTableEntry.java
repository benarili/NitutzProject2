package DataBase;

import Vacation.Vacation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacationTableEntry extends AdbEntry {

    public ArrayList<Vacation> selectByDates(long departureTime, long backLaunchTime) {
        ArrayList<Vacation> vacationsArrayList = new ArrayList<>();
        String sql;
        long[] values;
        if (backLaunchTime == 0) {
            sql = "SELECT * FROM vacations WHERE departureTime > ? AND avalible = 1;";
            values = new long[1];
            values[0] = departureTime;
        } else {
            sql = "SELECT * FROM vacations WHERE departureTime > ? AND departureTime < ? AND avalible = 1;";
            values = new long[2];
            values[0] = departureTime;
            values[0] = backLaunchTime;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            for (int i = 1; i <= values.length; i++) {
                pstmt.setLong(i, values[i - 1]);
            }
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                Vacation currRecord = new Vacation(rs.getInt("vacationID"),
                        rs.getString("seller"),
                        rs.getString("aviationCompany"),
                        rs.getLong("departureTime"),
                        rs.getLong("launchTime"),
                        rs.getLong("backDepartureTime"),
                        rs.getLong("backLaunchTime"),
                        rs.getInt("baggage"),
                        rs.getInt("tickets"),
                        rs.getString("fromCountry"),
                        rs.getString("destinationCountry"),
                        rs.getString("ticketType"),
                        rs.getDouble("price"),
                        rs.getInt("avalible"));
                vacationsArrayList.add(currRecord);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vacationsArrayList;
    }

    public boolean InsertComand(Vacation vacationToDB) {
        boolean success = false;
        String sqlCommand = "INSERT INTO vacations(vacationID,seller,aviationCompany,departureTime,launchTime," +
                "backDepartureTime,backLaunchTime,baggage,tickets,fromCountry,destinationCountry,ticketType," +
                "price,avalible) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {
            pstmt.setInt(1, vacationToDB.getVacationID());
            pstmt.setString(2, vacationToDB.getSeller());
            pstmt.setString(3, vacationToDB.getAviationCompany());
            pstmt.setLong(4, vacationToDB.getDepartureTime());
            pstmt.setLong(5, vacationToDB.getLaunchTime());
            pstmt.setLong(6, vacationToDB.getBackDepartureTime());
            pstmt.setLong(7, vacationToDB.getBackLaunchTime());
            pstmt.setInt(8, vacationToDB.getBaggage());
            pstmt.setInt(9, vacationToDB.getTickets());
            pstmt.setString(10, vacationToDB.getFromCountry());
            pstmt.setString(11, vacationToDB.getDestinationCountry());
            pstmt.setString(12, vacationToDB.getTicketType());
            pstmt.setDouble(13, vacationToDB.getPrice());
            int isAvalibleInt = 0;
            if (vacationToDB.isAvalible()) isAvalibleInt = 1;
            pstmt.setInt(14, isAvalibleInt);

            pstmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }


    public static void main(String[] args) {
        long d1 = 3;
        long d2 = 4;
        long d3 = 5;
        long d4 = 6;

        long d5 = 1;
        long d6 = 7;
//

        double p1 = 1000000;
        Vacation v1 = new Vacation("Liad","El-Al",d1,d2,d3,d4,20,2,"Isrel",
                "Colombia","adult",p1);
        VacationTableEntry vacationTableEntry = new VacationTableEntry();
        ArrayList<Vacation> arrayList = vacationTableEntry.selectByDates(d5,d6);
        if (arrayList.size() == 0) System.out.println("empty list");
        else System.out.println(arrayList.get(0).toString());
    }
}