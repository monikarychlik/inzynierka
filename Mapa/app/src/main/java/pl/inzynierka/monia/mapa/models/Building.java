package pl.inzynierka.monia.mapa.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Building extends RealmObject {

    @PrimaryKey
    private int id;

    private Identifier identifier;
    private double latitude;
    private double longitude;
    private RealmList<Unit> units;
    private RealmList<Faculty> faculties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public RealmList<Unit> getUnits() {
        return units;
    }

    public void setUnits(RealmList<Unit> units) {
        this.units = units;
    }

    public RealmList<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(RealmList<Faculty> faculties) {
        this.faculties = faculties;
    }
}
