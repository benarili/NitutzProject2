package Vacation;


import DataBase.TransactionTableEntry;
import DataBase.VacationTableEntry;
import User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vacation {
    private static int incrementID = 0;
    private int vacationID;
    private String sellerName;
    private String aviationCompany;

    private long departureTime;
    private long launchTime;
    private long backDepartureTime;
    private long backLaunchTime;

    private int baggage;
    private int tickets;
    private String fromCountry;
    private String destinationCountry;
    private String ticketType;
    private double price;

    private boolean isAvalible;

    public Vacation(String sellerName, String aviationCompany, long departureTime, long launchTime,
                    long backDepartureTime, long backLaunchTime, int baggage, int tickets,
                    String fromCountry, String destinationCountry, String ticketType, double price) {
        this.vacationID = ++incrementID;
        this.sellerName = sellerName;
        this.aviationCompany = aviationCompany;
        this.departureTime = departureTime;
        this.launchTime = launchTime;
        this.backDepartureTime = backDepartureTime;
        this.backLaunchTime = backLaunchTime;
        this.baggage = baggage;
        this.tickets = tickets;
        this.fromCountry = fromCountry;
        this.destinationCountry = destinationCountry;
        this.ticketType = ticketType;
        this.price = price;
        isAvalible = true;

        //add to dataBase
        VacationTableEntry vte = new VacationTableEntry();
        vte.InsertComand(this);
    }
    public Vacation(int vacationID, String sellerName, String aviationCompany, long departureTime, long launchTime,
                    long backDepartureTime, long backLaunchTime, int baggage, int tickets,
                    String fromCountry, String destinationCountry, String ticketType, double price, int isAvalible){
        //create object from record in DB
        // NO NEED TO ADD TO DB!

        this.vacationID = vacationID;
        this.sellerName = sellerName;
        this.aviationCompany = aviationCompany;
        this.departureTime = departureTime;
        this.launchTime = launchTime;
        this.backDepartureTime = backDepartureTime;
        this.backLaunchTime = backLaunchTime;
        this.baggage = baggage;
        this.tickets = tickets;
        this.fromCountry = fromCountry;
        this.destinationCountry = destinationCountry;
        this.ticketType = ticketType;
        this.price = price;
        this.isAvalible = isAvalible != 0;
    }

    public Vacation(String sellerUserName, int vacationID) {
        VacationTableEntry vacationTableEntry = new VacationTableEntry();
        String querry = "SELECT * FROM vacations WHERE  seller = ? AND vacationID = ?;";
        try (Connection conn = vacationTableEntry.connect();
             PreparedStatement pstmt  = conn.prepareStatement(querry)){
            pstmt.setString(1,sellerUserName);
            pstmt.setInt(2,vacationID);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                this.vacationID= rs.getInt("vacationID");
                this.sellerName=rs.getString("seller");
                this.aviationCompany=rs.getString("aviationCompany");
                this.departureTime=rs.getLong("departureTime");
                this.launchTime=rs.getLong("launchTime");
                this.backDepartureTime=rs.getLong("backDepartureTime");
                this.backLaunchTime=rs.getLong("backLaunchTime");
                this.baggage=rs.getInt("baggage");
                this.tickets=rs.getInt("tickets");
                this.fromCountry=rs.getString("fromCountry");
                this.destinationCountry=rs.getString("destinationCountry");
                this.ticketType=rs.getString("ticketType");
                this.price=rs.getDouble("price");
                if(rs.getInt("avalible") == 0) this.isAvalible = false;
                else this.isAvalible = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /* getters */
    public int getVacationID() {
        return vacationID;
    }

    public String getSeller() {
        return sellerName;
    }

    public String getAviationCompany() {
        return aviationCompany;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public long getLaunchTime() {
        return launchTime;
    }

    public long getBackDepartureTime() {
        return backDepartureTime;
    }

    public long getBackLaunchTime() {
        return backLaunchTime;
    }

    public int getBaggage() {
        return baggage;
    }

    public int getTickets() {
        return tickets;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvalible() {
        return isAvalible;
    }

    /* setters */
    public void changePrice(double price) {
        this.price = price;
    }

    public void setAvalible(boolean avalible) {
        isAvalible = avalible;
        //UPDATE IN DB!!!!
        VacationTableEntry vacationTableEntry = new VacationTableEntry();
        int boolInt = 0;
        if(avalible) boolInt = 1;
        vacationTableEntry.updateAvalible(getVacationID(),boolInt);
    }
    @Override
    public String toString() {
        return "seller: "+sellerName+"  country of origin: " + fromCountry+ "  country destination: "+destinationCountry+ "  price: " + price +"\ndepartureTime: " +departureTime
                + "  launchTime: " + launchTime+ "  backDepartureTime: "+backDepartureTime+"  backLaunchTime: "+backLaunchTime ;
    }
}
