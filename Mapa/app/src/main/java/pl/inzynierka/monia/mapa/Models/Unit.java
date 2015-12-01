package pl.inzynierka.monia.mapa.Models;

import io.realm.RealmObject;

public class Unit extends RealmObject {

    private Identifier identifier;
    private Contact contact;
    private int facultyID;
    private int[] buildingsID;

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

    public int[] getBuildingsID() {
        return buildingsID;
    }

    public void setBuildingID(int[] buildingsID) {
        this.buildingsID = buildingsID;
    }
}
