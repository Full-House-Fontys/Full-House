package AppCommunication;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class CommunicationMessage {
    private String text;
    private String sender;
    private String receiver;

    //TODO
    public String getText() {
        return text;
    }

    //TODO
    public String getSender() {
        return sender;
    }

    //TODO
    public void setSender(String sender) {
        this.sender = sender;
    }

    //TODO
    public String getReceiver() {
        return receiver;
    }

    //TODO
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    //TODO
    public void setText(String text) {
        this.text = text;
    }

    //TODO
    public CommunicationMessage(String text, String sender, String receiver){
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }
}
