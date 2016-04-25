package AppCommunication;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class CommunicationMessage {
    private String text;
    private String sender;
    private String receiver;

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommunicationMessage(String text, String sender, String receiver){
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }
}
