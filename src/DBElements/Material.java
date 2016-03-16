package DBElements;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class Material {
    private int id;
    private String name, sort;
    private double locationX, locationY;
    private boolean onLocation;

    public Material(int id, String name, String sort, double locationX, double locationY, boolean onLocation) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.locationX = locationX;
        this.locationY = locationY;
        this.onLocation = onLocation;
    }

    public Material(String name, String sort, double locationX, double locationY, boolean onLocation) {
        this.name = name;
        this.sort = sort;
        this.locationX = locationX;
        this.locationY = locationY;
        this.onLocation = onLocation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSort() {
        return sort;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public boolean isOnLocation() {
        return onLocation;
    }
}
