package pl.inzynierka.monia.mapa;

import java.util.ArrayList;
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

    public void search(String text) {
        final List<String> tempSuggestions = new ArrayList<>();

        findBuildings(text, tempSuggestions);

//        for (int i = 0; i < tempSuggestions.size(); i++) {
//            searchBox.addSearchable(new SearchResult(tempSuggestions.get(i)));
//        }
    }

    private void findBuildings(String text, List<String> tempSuggestions) {
        String wholeSearchedTextBuilding;

        for (Building building : buildings) {
            wholeSearchedTextBuilding = building.getIdentifier().getMarkLetter() +
                    building.getIdentifier().getMarkNumber() + ":" +
                    building.getIdentifier().getName();
            if(wholeSearchedTextBuilding.toUpperCase().contains(text.toUpperCase())){
                tempSuggestions.add(wholeSearchedTextBuilding);
            }

            findFaculties(text, tempSuggestions, building, wholeSearchedTextBuilding);
            findUnits(text, tempSuggestions, building, wholeSearchedTextBuilding);
        }
    }

    private void findUnits(String text, List<String> tempSuggestions, Building building,
                           String wholeSearchedTextBuilding) {
        String wholeSearchedText;
        for (Unit unit : building.getUnits()) {
            wholeSearchedText = unit.getIdentifier().getMarkLetter() +
                    unit.getIdentifier().getMarkNumber() + ":" +
                    unit.getIdentifier().getName();
            if(wholeSearchedText.toUpperCase().contains(text.toUpperCase())){
                tempSuggestions.add(wholeSearchedTextBuilding + "\n" + wholeSearchedText);
            }
        }
    }

    private void findFaculties(String text, List<String> tempSuggestions, Building building,
                               String wholeSearchedTextBuilding) {
        String wholeSearchedText;
        for (Faculty faculty : building.getFaculties()) {
            wholeSearchedText = faculty.getIdentifier().getMarkLetter() +
                    faculty.getIdentifier().getMarkNumber() + ":" +
                    faculty.getIdentifier().getName();
            if (wholeSearchedText.toUpperCase().contains(text.toUpperCase())) {
                tempSuggestions.add(wholeSearchedTextBuilding + "\n" + wholeSearchedText);
            }
        }
    }
}
