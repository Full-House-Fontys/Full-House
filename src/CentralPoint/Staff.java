package CentralPoint;

import java.awt.*;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class Staff {
    private String name;
    private String prefix;
    private String lastName;
    private String userName;
    private String password;
    private String sort;
    private Point2D location;
    private int teamID;
    private boolean onLocation;

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
     * @return teamID
     */
    public int getTeamID() {return teamID; }

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
     * @param name name
     * @param prefix prefix
     * @param lastName lastname
     * @param userName username
     * @param password password
     * @param location location Point2D
     * @param sort sort of work he does
     * @param teamID team he is currently in
     * @param onLocation boolean, true if on location
     */
    public Staff(String name, String prefix, String lastName, String userName, String password, Point2D location, String sort, int teamID, boolean onLocation) {
        this.name = name;
        this.prefix = prefix==null ? "":prefix;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.sort = sort;
        this.teamID = teamID;
        this.onLocation = onLocation;
    }

    /**
     * Empty staff object
     */
    public Staff(){}

    /**
     * @return String of staff variables
     */
    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", location=" + location +
                ", sort='" + sort + '\'' +
                ", teamID=" + teamID +
                ", onLocation=" + onLocation +
                '}';
    }
}
