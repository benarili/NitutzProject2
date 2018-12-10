package DataBase;

import Transactions.Transaction;

public class TransactionTableEntry extends AdbEntry {

    private String buyerUserName;
    private String sellerUserNAme;
    private String vacationID;
    private double amount;

    public TransactionTableEntry(Transaction transaction){
        this.buyerUserName =
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public String getSellerUserNAme() {
        return sellerUserNAme;
    }

    public String getVacationID() {
        return vacationID;
    }

    public double getAmount() {
        return amount;
    }

}
