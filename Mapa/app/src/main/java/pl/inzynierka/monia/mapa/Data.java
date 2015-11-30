package pl.inzynierka.monia.mapa;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.Models.Building;
import pl.inzynierka.monia.mapa.Models.Contact;
import pl.inzynierka.monia.mapa.Models.Faculty;
import pl.inzynierka.monia.mapa.Models.Identifier;
import pl.inzynierka.monia.mapa.Models.Unit;

public class Data {
    private static final char facultyMarkLetter = 'w';
    private int facultyID;
    private int buildingID;
    private int unitID;
    private Realm realm;

    public Data(Realm realm) {
        this.facultyID = 0;
        this.buildingID = 0;
        this.unitID = 0;
        this.realm = realm;
    }

    private Identifier createIdentifier(int id, String name, char markLetter, int markNumber) {
        realm.beginTransaction();

        final Identifier identifier = realm.createObject(Identifier.class);
        identifier.setId(id);
        identifier.setName(name);
        identifier.setMarkLetter(markLetter);
        identifier.setMarkNumber(markNumber);

        realm.commitTransaction();

        return identifier;
    }

    private Contact createContact(String email, String phoneNumber1, String phoneNumber2,
                                  String fax) {
        realm.beginTransaction();

        final Contact contact = realm.createObject(Contact.class);
        contact.setEmail(email);
        contact.setPhoneNumber1(phoneNumber1);
        contact.setPhoneNumber2(phoneNumber2);
        contact.setFax(fax);

        realm.commitTransaction();

        return contact;
    }

    public void createFaculty(String name, int number, String email, String phoneNumber1,
                               String phoneNumber2, String fax) {
        facultyID++;
        realm.beginTransaction();

        final Faculty faculty = realm.createObject(Faculty.class);
        faculty.setIdentifier(createIdentifier(facultyID, name, facultyMarkLetter, number));
        faculty.setContact(createContact(email, phoneNumber1, phoneNumber2, fax));

        realm.commitTransaction();
    }

    public void createUnit(String name, char markLetter, int markNumber, String email,
                           String phoneNumber1, String phoneNumber2, String fax, int facultyID,
                           int buildingID) {
        unitID++;
        realm.beginTransaction();

        final Unit unit = realm.createObject(Unit.class);
        unit.setIdentifier(createIdentifier(unitID, name, markLetter, markNumber));
        unit.setContact(createContact(email, phoneNumber1, phoneNumber2, fax));
        unit.setFacultyID(facultyID);
        unit.setBuildingID(buildingID);

        realm.commitTransaction();
    }

    public void createBuilding(String name, char markLetter, int markNumber, double latitude,
                                double longtitude) {
        buildingID++;
        realm.beginTransaction();

        final Building building = realm.createObject(Building.class);
        building.setIdentifier(createIdentifier(buildingID, name, markLetter, markNumber));
        building.setLatitude(latitude);
        building.setLongtitude(longtitude);

        realm.commitTransaction();
    }
}
