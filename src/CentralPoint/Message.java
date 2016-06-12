package CentralPoint;

/**
 * Created by Kees on 16/03/2016.
 */
public class Message {

    private String title;
    private String message;
    private int missionId;

    /**
     * The constructor for Message.
     * @param title : The title of the message.
     * @param message : The description of the message.
     * @param missionId : The ID of the message.
     */
    public Message(String title, String message, int missionId) {
        this.title = title;
        this.message = message;
        this.missionId = missionId;
    }

    /**
     * Gets the title of this Message.
     * @return the title.
     */
    public String getTitel() {
        return title;
    }

    /**
     * Gets the message of this Message.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the missionID of this Message.
     * @return the missionID.
     */
    public int getMissionId() {
        return missionId;
    }

}
