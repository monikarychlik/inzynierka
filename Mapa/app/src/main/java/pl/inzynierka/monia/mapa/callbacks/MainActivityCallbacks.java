package pl.inzynierka.monia.mapa.callbacks;

public interface MainActivityCallbacks {
    void passData(int buildingId);
    void changeToBuildingInfoFragment(String title);
    void changeToMapFragment(String title);
    void changeToBuildingsListFragment(String title);
    void changeToAboutFragment(String title);
    void changeToLessonPlanFragment(String title);
}
