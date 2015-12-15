package pl.inzynierka.monia.mapa;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Faculty;
import pl.inzynierka.monia.mapa.models.Unit;

public class Searcher {
    private RealmResults<Building> buildings;

    public Searcher(Realm realm) {
        this.buildings = realm.where(Building.class).findAll();
    }

    public void search(String text, List<Building> resultBuildings) {
        resultBuildings.clear();
        findBuildings(text, resultBuildings);
    }

    private void findBuildings(String text, List<Building> resultBuildings) {
        String wholeSearchedTextBuilding;

        for (Building building : buildings) {
            wholeSearchedTextBuilding = building.getIdentifier().getMarkLetter() +
                    building.getIdentifier().getMarkNumber() +
                    building.getIdentifier().getName();
            if(wholeSearchedTextBuilding.toUpperCase().contains(text.toUpperCase())){
                resultBuildings.add(building);
            } else {
                findFaculties(text, building, resultBuildings);
            }
        }
    }

    private void findUnits(String text, Building building, List<Building> resultBuildings) {
        String wholeSearchedText;
        for (Unit unit : building.getUnits()) {
            wholeSearchedText = unit.getIdentifier().getMarkLetter() +
                    unit.getIdentifier().getMarkNumber() +
                    unit.getIdentifier().getName();
            if(wholeSearchedText.toUpperCase().contains(text.toUpperCase())){
                resultBuildings.add(building);
            }
        }
    }

    private void findFaculties(String text, Building building, List<Building> resultBuildings) {
        String wholeSearchedText;
        for (Faculty faculty : building.getFaculties()) {
            wholeSearchedText = faculty.getIdentifier().getMarkLetter() +
                    faculty.getIdentifier().getMarkNumber() +
                    faculty.getIdentifier().getName();
            if (wholeSearchedText.toUpperCase().contains(text.toUpperCase())) {
                resultBuildings.add(building);
            } else {
                findUnits(text, building, resultBuildings);
            }
        }
    }
}
