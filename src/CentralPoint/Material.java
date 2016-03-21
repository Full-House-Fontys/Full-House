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

    public Material(int id, String name, String sort, Point2D location, boolean onLocation) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
    }

    public Material(String name, String sort, Point2D location, boolean onLocation) {
        this.name = name;
        this.sort = sort;
        this.location = location;
        this.onLocation = onLocation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Point2D getLocation() {
        return location;
    }

    public String getLocationString(){
        return location.getX() + "; " + location.getY();
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public boolean isOnLocation() {
        return onLocation;
    }

    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }
}
