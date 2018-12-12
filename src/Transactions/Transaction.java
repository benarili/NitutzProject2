
package Transactions;

import DataBase.*;
import User.*;
import Vacation.Vacation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Transactions.Payment;

public class Transaction {


    private Payment payment;
    private User buyer;
    private User seller;
    Vacation vacation;
    long dateUnixTime;
    boolean isInDB = false;

    public static Transaction createTransaction(User buyer, User seller, Vacation vacation){
        Payment payment = Payment.createPayment("hi","hello",vacation.getPrice());
        if(payment==null)
            return null;
        Transaction transaction = new Transaction(buyer,seller,vacation,payment);
        vacation.setAvalible(false);
        return transaction;
    }

    private Transaction(User buyer, User seller, Vacation vacation, Payment payment){
        this.buyer=buyer;
        this.seller=seller;
        this.vacation=vacation;
        this.payment = payment;
        dateUnixTime = new Date().getTime();
    }

    public void addToDataBase(){
        if(!isInDB) {
            TransactionTableEntry tb = new TransactionTableEntry();
            tb.insert(this);
            isInDB = true;
        }
    }

    public void removeFromDataBase(){
        if(isInDB){
            TransactionTableEntry tb = new TransactionTableEntry();
            tb.cancelTransaction(this);
            isInDB = false;
        }
    }

    public Transaction(String buyerUserName,String sellerUserName, int vacationID){
        TransactionTableEntry transactionTableEntry = new TransactionTableEntry();
        String querry = "SELECT buyerUserName,sellerUserName,vacationID,time FROM transactions WHERE buyerUserName=? AND sellerUserName=? AND vacationID=?";
        try (Connection conn = transactionTableEntry.connect();
             PreparedStatement pstmt  = conn.prepareStatement(querry)){
             pstmt.setString(1,buyerUserName);
             pstmt.setString(2,sellerUserName);
             pstmt.setInt(3,vacationID);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                this.vacation = new Vacation(sellerUserName,vacationID);
                this.seller = new User(sellerUserName);
                this.buyer = new User(buyerUserName);
                this.dateUnixTime = rs.getLong(4);
                this.isInDB=true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getBuyer() {
        return buyer;
    }

    public User getSeller() {
        return seller;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public long getDateUnixTime(){return dateUnixTime;}

    public String toString(){
        return "Transaction: "+this.buyer.getUsername()+" bought vacation:"+this.vacation.getVacationID()+" from "+this.seller.getUsername();
    }
}
