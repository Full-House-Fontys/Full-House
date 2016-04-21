package AppCommunication;

/**
 * Created by Kaj Suiker on 13-4-2016.
 */
public class CommunicationMessage {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommunicationMessage(String text){
        this.text = text;
    }
}
