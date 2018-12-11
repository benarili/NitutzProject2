package Vacation;


import DataBase.VacationTableEntry;

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
        // NEED TO UPDATE IN DB!!!!
    }
//ddsd
    @Override
    public String toString() {
        return "vacationID: "+vacationID+"seller: "+sellerName+"departureTime: " +departureTime
                + "launchTime: " + launchTime+ "backDepartureTime: "+backDepartureTime+"backLaunchTime: "+backLaunchTime;
    }
}
