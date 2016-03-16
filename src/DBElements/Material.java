package DBElements;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class Material {
    private int id;
    private String naam, soort;
    private double locatieX, locatieY;
    private boolean opLocatie;

    public Material(int id, String naam, String soort, double locatieX, double locatieY, boolean opLocatie) {
        this.id = id;
        this.naam = naam;
        this.soort = soort;
        this.locatieX = locatieX;
        this.locatieY = locatieY;
        this.opLocatie = opLocatie;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getSoort() {
        return soort;
    }

    public double getLocatieX() {
        return locatieX;
    }

    public double getLocatieY() {
        return locatieY;
    }

    public boolean isOpLocatie() {
        return opLocatie;
    }
}
