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
     * Constructor of staff with teamID and MissionID
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

    /**
     * Constructor without teamID and MissionID
     *
     * @param iD
     * @param name
     * @param prefix
     * @param lastName
     * @param userName
     * @param password
     * @param location
     * @param sort
     * @param onLocation
     */
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

    /**
     * The username of the Person
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The password of the staff
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the staff
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the password of the staff
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * ID of the staff
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the staff
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Empty staff object
     */
    public Staff() {
    }


    /**
     * The id of the team the staffmember is registered in
     * @return teamID
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * The id of the mission the staffmember
     * @return missionID
     */
    public int getMissionID() {
        return missionID;
    }

    /**
     * The last name of the staffmember
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The type of helpservice
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * The name of the staffmember
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * The X and Y locations of the staffmember
     * @return 2D location
     */
    public Point2D getLocation() {return location; }


    /**
     * The location converted in a string
     * @return String op location, rounded to 2 decimals
     */
    public String getLocationString(){return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " +new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue(); }

    /**
     * Shows if the staffmember is on location
     * @return boolean of is on location
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * Changes the location of the staffmember
     * @param onLocation boolean, true if on location
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    /**
     * Returns the type of staff it is
     * @return String of staff variables
     */
    @Override
    public String toString() {
        return sort;
    }
}
