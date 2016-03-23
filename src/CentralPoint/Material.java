package CentralPoint;

import java.awt.geom.Point2D;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class Material {
    private int id;
    private String name, sort;
    private Point2D location;
    private boolean onLocation;

    /**
     * constructor material with id
     * @param id
     * @param name
     * @param sort
     * @param location
     * @param onLocation
     */
    public Material(int id, String name, String sort, Point2D location, boolean onLocation) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
    }

    /**
     * constructor material without id
     * @param name
     * @param sort
     * @param location
     * @param onLocation
     */
    public Material(String name, String sort, Point2D location, boolean onLocation) {
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * sets the sort
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     *
     * @return location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     *
     * @return location as string
     */
    public String getLocationString(){
        return location.getX() + "; " + location.getY();
    }

    /**
     * sets the location
     * @param location
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     *
     * @return onLocation
     */
    public boolean isOnLocation() {
        return onLocation;
    }

    /**
     * sets the onLocation
     * @param onLocation
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }
}
