package CentralPoint;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class Staff {
    private String name;
    private String prefix;
    private String lastName;
    private String userName;
    private String password;
    private Point2D location;
    private String sort;
    private int teamID;
    private boolean onLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isOnLocation() {
        return onLocation;
    }

    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

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

    public Staff(){}

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
