package AppCommunication;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class CommunicationMessage {
    private String text;
    private String sender;
    private String receiver;

    /**
     * Constructor.
     * CommunicationMessage low level version of CommunicationRequest.
     * Remembers the ip address of client and host
     * Used to create CommunicationRequest
     * @param text : Total data send using socket
     * @param sender : ip of sender, App
     * @param receiver : ip of receiver, Central point server
     */
    public CommunicationMessage(String text, String sender, String receiver){
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Gets the message of this CommunicationMessage.
     * @return the message.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets a new message to this CommunicationMessage.
     * @param text : the new message.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the sender of this CommunicationMessage.
     * @return the sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets a new sender to this CommunicationMessage.
     * @param sender : the new sender.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the receiver of this CommunicationMessage.
     * @return the receiver.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets a new receiver to this CommunicationMessage.
     * @param receiver : the new receiver.
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
