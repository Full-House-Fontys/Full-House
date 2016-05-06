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

    public Notification(int ID, String title, String content, Date time, int missionID, int userID) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.time = time;
        this.missionID = missionID;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
