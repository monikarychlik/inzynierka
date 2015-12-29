package pl.inzynierka.monia.mapa.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Unit extends RealmObject {

    @PrimaryKey
    private int id;

    private Identifier identifier;
    private Contact contact;
    private int facultyID;
    private RealmList<BuildingID> buildingsIDs;

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public RealmList<BuildingID> getBuildingsIDs() {
        return buildingsIDs;
    }

    public void setBuildingsIDs(RealmList<BuildingID> buildingsIDs) {
        this.buildingsIDs = buildingsIDs;
    }
}
