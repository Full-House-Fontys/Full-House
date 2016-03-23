package CentralPoint;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    }

    /**
     * constructor material without id
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
     * @param name name
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
     * @param sort sort
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
    public String getLocationString(){return new BigDecimal(location.getX()).setScale(2, RoundingMode.HALF_UP).doubleValue() + "; " +new BigDecimal(location.getY()).setScale(2, RoundingMode.HALF_UP).doubleValue(); }

    /**
     * sets the location
     * @param location location
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
     * @param onLocation onLocation
     */
    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }
}
