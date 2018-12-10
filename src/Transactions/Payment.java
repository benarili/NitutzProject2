package Transactions;
import User.*;
public class Payment {

    User from;
    User to;
    double amount;

    public static Payment createPayment(User from, User to, double amount){
        PaypalInterface paypalInterface = PaypalInterface.getInstance();
        /*
        if (paypalInterface.makePayment(from.getPaypalUserName(),to.getPayPalUserName(), amount){
            return new Payment(from,to,amount);
        }
        */
        return null;
    }

    private Payment(User from, User to, double amount){
        this.from=from;
        this.to = to;
        this.amount = amount;
    }
}
