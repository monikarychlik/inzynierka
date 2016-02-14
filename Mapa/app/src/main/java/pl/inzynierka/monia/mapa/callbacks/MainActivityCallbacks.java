package pl.inzynierka.monia.mapa.callbacks;

import io.realm.RealmList;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;

public interface MainActivityCallbacks {
    void passBuildingsIdToMap(int buildingId);
    void passBuildingsIdToInfo(int buildingId);
    void passBuildingsIDs(RealmList<BuildingID> buildingIDs);
    void passDataToMap(int radioButtonTypeOfTravelId, boolean navigateFromMyLocation,
                       Building pointA, Building pointB);
    void passUnitId(int id);
    void stopNavigation();
    void changeToBuildingInfoFragment(String title);
    void changeToMapFragment(String title);
    void changeToBuildingsListFragment(String title);
    void changeToAboutFragment(String title);
    void changeToLessonPlanFragment(String title);
    void changeToUnitInfoFragment(String title);
    void changeToNavigationFragment(String title);
}
