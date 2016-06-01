package CentralPoint;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class Material implements Serializable {
    private int id;
    private String name, sort;
    private Point2D location;
    private boolean onLocation;
    private double distance;
    private ArrayList<Integer> missionIds;

    /**
     * Constructor material with id
     * @param id id
     * @param name name
     * @param sort sort
     * @param location location
     * @param onLocation onLocation
     */
    public Material(int id, String name, String sort, Point2D location, boolean onLocation) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
        this.missionIds = new ArrayList();
    }

    /**
     * Constructor material without id
     * @param name name
     * @param sort sort
     * @param location location
     * @param onLocation onLocation
     */
    public Material(String name, String sort, Point2D location, boolean onLocation) {
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
        this.missionIds = new ArrayList();
    }

    /**
     * Returns the id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the sort
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * Sets the sort
     * @param sort sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Returns the location
     * @return location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets the location
     * @param location location
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * Returns the location as a string like "xx.xx; yy.yy"
     * @return location as string
     */
    public String getLocationString() {
        return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " + new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Returns the distance between two points
     *
     * @return
     */
    public String getDistance() {
        return new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP).doubleValue() + " km";
    }

    /**
     * Sets the distance between the material location and another location
     * @param otherLocation
     */
    public void setDistance(Point2D otherLocation) {
        distance = distance(location, otherLocation);
    }

    /**
     * Returns the ids of the missions the material is connected to
     * @return list of missionids
     */
    public ArrayList<Integer> getMissionIds() {
        return missionIds;
    }

    /**
     * Sets the missionids where the material is connected to
     * @param missionIds as int
     */
    public void setMissionIds(ArrayList<Integer> missionIds) {
        this.missionIds = missionIds;
    }

    /**
     * Adds one mission id
     * @param id as int
     */
    public void addMissionId(int id) {
        this.missionIds.add(id);
    }

    /**
     * Returns true if material is on the location
     * @return onLocation
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * Sets the onLocation
     * @param onLocation onLocation
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    /**
     * Calculates the distance between the material point and the given point
     *
     * @param loc1 the loc from this material
     * @param loc2 the other location
     * @param unit the unit; K = KM
     * @return the distance in the given unit
     */
    private double distance(Point2D loc1, Point2D loc2) {
        double theta = loc1.getY() - loc2.getY();
        double dist = Math.sin(deg2rad(loc1.getX())) * Math.sin(deg2rad(loc2.getX())) + Math.cos(deg2rad(loc1.getX())) * Math.cos(deg2rad(loc2.getX())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }

    /**
     * Degrees to radians
     * @param deg as double
     * @return the radians
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Radians to degrees
     * @param rad as double
     * @return the degrees
     */
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
