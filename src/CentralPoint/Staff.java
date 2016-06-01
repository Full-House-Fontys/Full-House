package CentralPoint;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class Staff implements Serializable {
    private String name;
    private String prefix;
    private String lastName;
    private String userName;
    private String password;
    private String sort;
    private Point2D location;
    private boolean onLocation;
    private int id;
    private int teamID;
    private int missionID;

    /**
     * TODO ADD DESCRIPTION
     * @param name name
     * @param prefix prefix
     * @param lastName lastname
     * @param userName username
     * @param password password
     * @param location location Point2D
     * @param sort sort of work he does
     * @param onLocation boolean, true if on location
     */
    public Staff(int iD, String name, String prefix, String lastName, String userName, String password, Point2D location, String sort, boolean onLocation, int teamID, int missionID) {
        this.name = name;
        this.prefix = prefix == null ? "" : prefix;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.sort = sort;
        this.onLocation = onLocation;
        this.id = iD;
        this.teamID = teamID;
        this.missionID = missionID;
    }

    //TODO
    public Staff(int iD, String name, String prefix, String lastName, String userName, String password, Point2D location, String sort, boolean onLocation){
        this.name = name;
        this.prefix = prefix == null ? "" : prefix;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.sort = sort;
        this.onLocation = onLocation;
        this.id = iD;
    }

    //TODO
    public String getUserName() {
        return userName;
    }

    //TODO
    public String getPassword() {
        return password;
    }

    //TODO
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //TODO
    public void setPassword(String password) {
        this.password = password;
    }

    //TODO
    public int getId() {
        return id;
    }

    //TODO
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Empty staff object
     */
    public Staff() {
    }

    //TODO
    public int getTeamID() {
        return teamID;
    }

    //TODO
    public int getMissionID() {
        return missionID;
    }

    //TODO
    public String getLastName() {
        return lastName;
    }

    /**
     * TODO ADD DESCRIPTION
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * TODO ADD DESCRIPTION
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * TODO ADD DESCRIPTION
     * @return 2D location
     */
    public Point2D getLocation() {return location; }


    /**
     * TODO ADD DESCRIPTION
     * @return String op location, rounded to 2 decimals
     */
    public String getLocationString(){return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " +new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue(); }

    /**
     * TODO ADD DESCRIPTION
     * @return boolean of is on location
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * TODO ADD DESCRIPTION
     * @param onLocation boolean, true if on location
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    /**
     * TODO ADD DESCRIPTION
     * @return String of staff variables
     */
    @Override
    public String toString() {
        return sort;
    }
}
