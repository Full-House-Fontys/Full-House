package CentralPoint;

/**
 * Created by Kees on 16/03/2016.
 */
public class Message {

    private String titel;
    private String message;

    public String getTitel() {
        return titel;
    }

    public String getMessage() {
        return message;
    }

    public Message(String titel, String message){
        this.titel = titel;
        this.message = message;
    }

}
