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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Empty staff object
     */
    public Staff() {
    }

    public int getTeamID() {
        return teamID;
    }

    public int getMissionID() {
        return missionID;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return 2D location
     */
    public Point2D getLocation() {return location; }


    /**
     * @return String op location, rounded to 2 decimals
     */
    public String getLocationString(){return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " +new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue(); }

    /**
     * @return boolean of is on location
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * @param onLocation boolean, true if on location
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    /**
     * @return String of staff variables
     */
    @Override
    public String toString() {
        return sort;
    }
}
