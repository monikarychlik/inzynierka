package pl.inzynierka.monia.mapa.utils;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;
import pl.inzynierka.monia.mapa.models.Contact;
import pl.inzynierka.monia.mapa.models.Faculty;
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.models.Unit;

public class Data {
    private static final String facultyMarkLetter = "w";
    private int facultyID;
    private int buildingID;
    private int unitID;
    private Realm realm;

    public Data(Context context) {
        this.facultyID = 0;
        this.buildingID = 0;
        this.unitID = 0;
        this.realm = Realm.getInstance(context);
    }

    private Identifier createIdentifier(String name, String markLetter, int markNumber) {
        final Identifier identifier = new Identifier();
        identifier.setName(name);
        identifier.setMarkLetter(markLetter);
        identifier.setMarkNumber(markNumber);

        realm.beginTransaction();
        realm.copyToRealm(identifier);
        realm.commitTransaction();

        return identifier;
    }

    private Contact createContact(String email, String phoneNumber1, String phoneNumber2,
                                  String fax) {
        final Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhoneNumber1(phoneNumber1);
        contact.setPhoneNumber2(phoneNumber2);
        contact.setFax(fax);

        realm.beginTransaction();
        realm.copyToRealm(contact);
        realm.commitTransaction();

        return contact;
    }

    public void createFaculty(String name, int number, String email, String phoneNumber1,
                               String phoneNumber2, String fax) {
        facultyID++;

        final Faculty faculty = new Faculty();
        faculty.setId(facultyID);
        faculty.setIdentifier(createIdentifier(name, facultyMarkLetter, number));
        faculty.setContact(createContact(email, phoneNumber1, phoneNumber2, fax));

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(faculty);
        realm.commitTransaction();
    }

    public void createUnit(String name, String markLetter, int markNumber, String email,
                           String phoneNumber1, String phoneNumber2, String fax, int facultyID,
                           RealmList<BuildingID> buildingsID) {
        unitID++;

        final Unit unit = new Unit();
        unit.setId(unitID);
        unit.setIdentifier(createIdentifier(name, markLetter, markNumber));
        unit.setContact(createContact(email, phoneNumber1, phoneNumber2, fax));
        unit.setFacultyID(facultyID);
        unit.setBuildingsIDs(buildingsID);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(unit);
        realm.commitTransaction();
    }

    public void createBuilding(String name, String markLetter, int markNumber, double latitude,
                                double longitude) {
        buildingID++;

        final Building building = new Building();

        building.setId(buildingID);
        building.setIdentifier(createIdentifier(name, markLetter, markNumber));
        building.setLatitude(latitude);
        building.setLongitude(longitude);

        findAndSetUnitsAndFacultiesInBuilding(building);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(building);
        realm.commitTransaction();
    }

    private void findAndSetUnitsAndFacultiesInBuilding(Building building) {
        final RealmList<Unit> unitsInBuilding = new RealmList<>();
        final RealmList<Faculty> faculties = new RealmList<>();
        final List<Unit> allUnits = realm.where(Unit.class).findAll();

        for (Unit unit : allUnits) {
            for (BuildingID buildingID : unit.getBuildingsIDs()) {
                if (buildingID.getBuildingID() == building.getId()) {
                    unitsInBuilding.add(unit);
                    addFaculty(faculties, unit);
                    break;
                }
            }
        }

        building.setUnits(unitsInBuilding);
        building.setFaculties(faculties);
    }

    private void addFaculty(List<Faculty> faculties, Unit unit) {
        final Faculty newFaculty = realm.where(Faculty.class).equalTo(
                "id", unit.getFacultyID()).findFirst();

        for (Faculty faculty : faculties) {
            if (newFaculty.getId() == faculty.getId()) {
                return;
            }
        }

        faculties.add(newFaculty);
    }
}
