package CentralPoint;

/**
 * Created by Kees on 16/03/2016.
 */
public class Message {

    private String title;
    private String message;
    private int missionId;


    /**
     * The constructor for message
     *
     * @param title
     * @param message
     * @param missionId
     */
    public Message(String title, String message, int missionId) {
        this.title = title;
        this.message = message;
        this.missionId = missionId;
    }


    /**
     * Returns the title of the message
     *
     * @return title of the message
     */
    public String getTitel() {
        return title;
    }

    /**
     * Returns the description of the message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Return the id of the mission
     * @return missionId
     */
    public int getMissionId() {
        return missionId;
    }

}
