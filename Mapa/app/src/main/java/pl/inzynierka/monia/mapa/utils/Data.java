package pl.inzynierka.monia.mapa.utils;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
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
    private RealmList<Unit> units = new RealmList<>();
    private RealmList<Faculty> faculties = new RealmList<>();

    public Data(Realm realm) {
        this.facultyID = 0;
        this.buildingID = 0;
        this.unitID = 0;
        this.realm = realm;
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

        final RealmResults<Building> buildings = realm.where(Building.class).findAll();
        Faculty unitFaculty = getFaculty(facultyID);

        for (int i = 0; i < buildings.size(); i++) {
            for (BuildingID id : buildingsID) {
                if (buildings.get(i).getId() == id.getBuildingID()) {
                    updateBuilding(unit, unitFaculty, buildings.get(i));
                }
            }
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(unit);
        realm.commitTransaction();
    }

    @NonNull
    private Faculty getFaculty(int facultyID) {
        final RealmResults<Faculty> faculties = realm.where(Faculty.class).findAll();
        Faculty unitFaculty = new Faculty();

        for (Faculty faculty : faculties) {
            if (faculty.getId() == facultyID) {
                unitFaculty = faculty;
            }
        }
        return unitFaculty;
    }

    private void updateBuilding(Unit unit, Faculty unitFaculty, Building building) {
        final Building newBuilding = new Building();
        final RealmList<Unit> tempUnits = building.getUnits();
        final RealmList<Faculty> tempFaculties = building.getFaculties();

        realm.beginTransaction();
        newBuilding.setId(building.getId());
        newBuilding.setIdentifier(building.getIdentifier());
        newBuilding.setLatitude(building.getLatitude());
        newBuilding.setLongitude(building.getLongitude());

        tempUnits.add(unit);
        newBuilding.setUnits(tempUnits);

        tempFaculties.add(unitFaculty);
        newBuilding.setFaculties(tempFaculties);

        realm.copyToRealmOrUpdate(newBuilding);
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
        building.setUnits(units);
        building.setFaculties(faculties);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(building);
        realm.commitTransaction();
    }
}
