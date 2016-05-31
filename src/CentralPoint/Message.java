package CentralPoint;

/**
 * Created by Kees on 16/03/2016.
 */
public class Message {

    private String titel;
    private String message;
    private int missionId;

    public Message(String titel, String message, int missionId) {
        this.titel = titel;
        this.message = message;
        this.missionId = missionId;
    }

    public String getTitel() {
        return titel;
    }

    public String getMessage() {
        return message;
    }

    public int getMissionId() {
        return missionId;
    }

}
