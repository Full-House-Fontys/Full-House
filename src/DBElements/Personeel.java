package DBElements;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class Personeel {
    private String name;
    private String lastName;
    private Point2D location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Personeel(){
        this.name = name;
        this.lastName = lastName;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Personeel{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location=" + location +
                '}';
    }
}
