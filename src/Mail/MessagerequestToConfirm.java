package Mail;

import Transactions.Transaction;
import User.*;

public class MessagerequestToConfirm extends Message{

    Transaction toConfirm;
    ConfirmationAction confirmationAction;


    MessagerequestToConfirm(boolean isRead, String text, User from, User to, int id, Transaction toConfirm, ConfirmationAction confirmationAction) {
        super(isRead, text, from, to, id);
        this.toConfirm = toConfirm;
        this.confirmationAction = confirmationAction;
    }

    public MessagerequestToConfirm(User from, User to, Transaction toConfirm, ConfirmationAction confirmationAction) {
        super(from,to);
        this.confirmationAction=confirmationAction;
        this.toConfirm=toConfirm;
        setText(textMessage(confirmationAction,toConfirm));

    }

    public void confirm(boolean answer){

    }

    protected String getType(){
        return "confirmation";
    }

    private String textMessage(ConfirmationAction confirmationAction, Transaction t){
        String toReturn = null;
        switch (confirmationAction){
            case CONFIRMSALE:
                toReturn = "User: "+t.getBuyer().getUsername()+" wants to buy vacation:"+t.getVacation().getVacationID()+" from you.";
                break;
            case CONFIRMCANCEL:
                toReturn = "User: "+t.getBuyer().getUsername()+" wants to cancel vacation:"+t.getVacation().getVacationID();
        }
        return toReturn;
    }


    public enum  ConfirmationAction{
        CONFIRMSALE,CONFIRMCANCEL;
    }



}
