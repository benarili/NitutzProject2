package Mail;

import Transactions.Transaction;
import User.*;
import Vacation.Vacation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageRequestToConfirm extends Message{

    Vacation vacationToConfirm;
    boolean haveResponded;


    MessageRequestToConfirm(boolean isRead, String savedText, String usernameFrom, String usernameTo, int id) {
        super(isRead, savedText, usernameFrom, usernameTo, id);
        setTextFromSavedText(savedText);
    }

    private MessageConfirmedPurchase.Type accept(){
        if(vacationToConfirm.isAvalible()){
            Transaction transaction = Transaction.createTransaction(getFromUser(),getTo(),vacationToConfirm);
            if(transaction==null)
                return MessageConfirmedPurchase.Type.UNABLETOCOMPLETEPURCHASE;
            transaction.addToDataBase();
            vacationToConfirm.setAvalible(false);
            return MessageConfirmedPurchase.Type.COMPLETEDTRANSACTION;
        }
        else
            return MessageConfirmedPurchase.Type.FLIGHTNOTAVAILABLE;
    }

    public void confirm(boolean action){
        haveResponded = true;
        MessageConfirmedPurchase.Type type;
        if (action==true){
            type = accept();
        }
        else {
            type = MessageConfirmedPurchase.Type.USERREGECTED;
        }
        Mailbox sellerMailBox = getTo().getMailbox();
        Mailbox buyerMailBox = getFromUser().getMailbox();
        Message message = new MessageConfirmedPurchase(vacationToConfirm,type,getFromUser());
        sellerMailBox.sendMessage(message,buyerMailBox.getOwnerUserName());
        buyerMailBox.sendMessage(message,sellerMailBox.getOwnerUserName());
    }



    public String getType(){
        return "request_confirmation";
    }



    @Override
    public String getText() {
        if(haveResponded && vacationToConfirm.isAvalible()){
            return "You can no longer confirm sale of message. The vacation may no longer be available or you have already responded to this message";
        }
        else {
            return "User: "+getUserNameFrom()+" wants to purchase vacation:" + vacationToConfirm.getVacationID()+"\n" +
                    "Choose your response";
        }
    }




    public void setTextFromSavedText(String savedText){
        List<String> splitText = new ArrayList<>();
        splitText.addAll(Arrays.asList(savedText.split("\n")));
        String haveResponded = splitText.remove(0);
        String vacationKeys = splitText.remove(splitText.size()-1);

        //set text
        String text = "";
        for (int i = 0; i < splitText.size(); i++) {
            text+=splitText.get(i)+"\n";
        }
        text = text.substring(0,text.length()-1);
        setText(text);

        //set vacation
        String[] vacationKeysSplit = vacationKeys.split("\t");
        this.vacationToConfirm = new Vacation(vacationKeysSplit[0],Integer.parseInt(vacationKeysSplit[1]));

        //set have read
        this.haveResponded = vacationToConfirm.isAvalible() && haveResponded.equals("t");
    }



    public String getTextToSave(){
        String vacation = vacationToConfirm.getSeller()+"\t"+vacationToConfirm.getVacationID();
        String responded = this.haveResponded ? "t" : "f";
        String messageText = this.text;

        return responded+"\n"+messageText+"\n"+vacation;
    }
}
