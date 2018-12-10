package Vacation;


import java.util.Date;

public class Vacation {
    private String vacationID;
    private User seller;
    private String aviationCompany;

    private Date departureTime;
    private Date launchTime;
    private Date backDepartureTime;
    private Date backLaunchTime;

    private int baggage;
    private int tickets;
    private String fromCountry;
    private String destinationCountry;
    private String ticketType;
    private double price;

    private boolean isAvalible;

    public Vacation(User seller, String aviationCompany, Date departureTime, Date launchTime,
                    Date backDepartureTime, Date backLaunchTime, int baggage, int tickets,
                    String fromCountry, String destinationCountry, String ticketType, double price) {
        this.seller = seller;
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
        long departureTimeForSQL = departureTime.getTime()/1000;
        long launchTimeForSQL = launchTime.getTime()/1000;
        if (backDepartureTime != null && backLaunchTime != null ){
            long backDepartureTimeForSQL = backDepartureTime.getTime()/1000;
            long backLaunchTimeForSQL = backLaunchTime.getTime()/1000;
        }

    }

    /* getters */
    public String getVacationID() {
        return vacationID;
    }

    public User getSeller() {
        return seller;
    }

    public String getAviationCompany() {
        return aviationCompany;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public Date getBackDepartureTime() {
        return backDepartureTime;
    }

    public Date getBackLaunchTime() {
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
    }


}
