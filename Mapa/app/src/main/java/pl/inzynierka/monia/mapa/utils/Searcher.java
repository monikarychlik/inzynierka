package pl.inzynierka.monia.mapa.utils;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Faculty;
import pl.inzynierka.monia.mapa.models.Unit;

public class Searcher {
    private RealmResults<Building> buildings;
    private RealmResults<Unit> units;

    public Searcher(Context context) {
        final Realm realm = Realm.getInstance(context);
        this.buildings = realm.where(Building.class).findAll();
        this.units = realm.where(Unit.class).findAll();
    }

    public void searchAll(String text, List<Building> resultBuildings) {
        resultBuildings.clear();
        findBuildings(true, text, resultBuildings);
    }

    public void searchUnits(String text, List<Unit> resultUnits) {
        resultUnits.clear();
        findUnits(text, resultUnits);
    }

    private void findUnits(String text, List<Unit> resultUnits) {
        String wholeSearchedText;

        for (Unit unit : units) {
            wholeSearchedText = unit.getIdentifier().getMarkLetter() +
                    unit.getIdentifier().getMarkNumber() +
                    unit.getIdentifier().getName();
            if(wholeSearchedText.toUpperCase().contains(text.toUpperCase())){
                resultUnits.add(unit);
            }
        }
    }

    public void searchBuildings(String text, List<Building> resultBuildings) {
        resultBuildings.clear();
        findBuildings(false, text, resultBuildings);
    }

    private void findBuildings(boolean findMore, String text, List<Building> resultBuildings) {
        String wholeSearchedTextBuilding;

        for (Building building : buildings) {
            wholeSearchedTextBuilding = building.getIdentifier().getMarkLetter() +
                    building.getIdentifier().getMarkNumber() +
                    building.getIdentifier().getName();
            if(wholeSearchedTextBuilding.toUpperCase().contains(text.toUpperCase())){
                resultBuildings.add(building);
            } else if (findMore) {
                findFacultiesInBuilding(text, building, resultBuildings);
            }
        }
    }

    private void findUnitsInBuilding(String text, Building building, List<Building> resultBuildings) {
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

    private void findFacultiesInBuilding(String text, Building building, List<Building> resultBuildings) {
        String wholeSearchedText;
        for (Faculty faculty : building.getFaculties()) {
            if (faculty.getId() != 0) {
                wholeSearchedText = faculty.getIdentifier().getMarkLetter() +
                        faculty.getIdentifier().getMarkNumber() +
                        faculty.getIdentifier().getName();
                if (wholeSearchedText.toUpperCase().contains(text.toUpperCase())) {
                    resultBuildings.add(building);
                } else {
                    findUnitsInBuilding(text, building, resultBuildings);
                }
            }
        }
    }
}
