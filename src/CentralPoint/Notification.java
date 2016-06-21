package CentralPoint;

import java.util.Date;

/**
 * Created by fhict on 06-05-16.
 */
public class Notification {
    private int ID;
    private String title;
    private String content;
    private Date time;
    private int missionID;
    private int userID;

    /**
     * Constructor of the notication
     *
     * @param ID
     * @param title
     * @param content
     * @param time
     * @param missionID
     * @param userID
     */
    public Notification(int ID, String title, String content, Date time, int missionID, int userID) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.time = time;
        this.missionID = missionID;
        this.userID = userID;
    }


    /**
     * The ID of the notification
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the notification
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * The title of the Notification
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Notification
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The message of the Notification
     * @return Content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the Notification
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * The moment the Notification is made
     * @return Time of the Notification
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets the time the Notification is made
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Returns the missionID which is binded by the Notification
     * @return MissionID
     */
    public int getMissionID() {
        return missionID;
    }

    /**
     * Sets the missionID of the notification
     * @param missionID
     */
    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    /**
     * The userID of the person who made the notification
     * @return UserID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the userID of the person who made the notification
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Instead of returning all parameters
     * it return the content
     * @return this content
     */
    @Override
    public String toString() {
        return this.title;
    }
}
