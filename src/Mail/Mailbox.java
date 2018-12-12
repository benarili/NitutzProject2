package Mail;

import Transactions.Transaction;
import User.*;

import java.util.ArrayList;
import java.util.List;

public class Mailbox {

    private User owner;
    private List<Message> messages;

    public Mailbox(User owner){
        this.owner=owner;
    }

    public List<Message> getMessages(){return messages;}

    public void sendMessage(User userOther,String messageText){
        Mailbox mailboxOther = userOther.getMailbox();
        Message toSend = new Message(messageText,owner,userOther);
        mailboxOther.addMessage(toSend);
    }

    public void sendConfirmationMessage(User userOther, Transaction toConfirm){
        Mailbox mailboxOther = userOther.getMailbox();
        MessagerequestToConfirm messagerequestToConfirm = new MessagerequestToConfirm(owner,userOther,toConfirm,confirmationAction);
        mailboxOther.addMessage(messagerequestToConfirm);
    }

    private void addMessage(Message message){
        this.messages.add(0,message);
    }

    private static Message createFromEntry()



}
