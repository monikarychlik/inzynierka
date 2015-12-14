package pl.inzynierka.monia.mapa.models;

import io.realm.RealmObject;

public class BuildingID extends RealmObject {

    private int buildingID;

    public BuildingID(){
        this.buildingID = 0;
    }

    public BuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }
}
