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

    //TODO
    public Notification(int ID, String title, String content, Date time, int missionID, int userID) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.time = time;
        this.missionID = missionID;
        this.userID = userID;
    }

    //TODO
    public int getID() {
        return ID;
    }

    //TODO
    public void setID(int ID) {
        this.ID = ID;
    }

    //TODO
    public String getTitle() {
        return title;
    }

    //TODO
    public void setTitle(String title) {
        this.title = title;
    }

    //TODO
    public String getContent() {
        return content;
    }

    //TODO
    public void setContent(String content) {
        this.content = content;
    }

    //TODO
    public Date getTime() {
        return time;
    }

    //TODO
    public void setTime(Date time) {
        this.time = time;
    }

    //TODO
    public int getMissionID() {
        return missionID;
    }

    //TODO
    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    //TODO
    public int getUserID() {
        return userID;
    }

    //TODO
    public void setUserID(int userID) {
        this.userID = userID;
    }

    //TODO
    @Override
    public String toString() {
        return this.content;
    }
}
