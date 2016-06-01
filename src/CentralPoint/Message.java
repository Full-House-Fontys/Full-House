package CentralPoint;

/**
 * Created by Kees on 16/03/2016.
 */
public class Message {

    private String titel;
    private String message;
    private int missionId;

    //TODO
    public Message(String titel, String message, int missionId) {
        this.titel = titel;
        this.message = message;
        this.missionId = missionId;
    }

    //TODO
    public String getTitel() {
        return titel;
    }

    //TODO
    public String getMessage() {
        return message;
    }

    //TODO
    public int getMissionId() {
        return missionId;
    }

}
