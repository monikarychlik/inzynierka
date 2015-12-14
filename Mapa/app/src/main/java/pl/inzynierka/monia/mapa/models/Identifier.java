package pl.inzynierka.monia.mapa.models;

import io.realm.RealmObject;

public class Identifier extends RealmObject {

    private String name;
    private String markLetter;
    private int markNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarkLetter() {
        return markLetter;
    }

    public void setMarkLetter(String markLetter) {
        this.markLetter = markLetter;
    }

    public int getMarkNumber() {
        return markNumber;
    }

    public void setMarkNumber(int markNumber) {
        this.markNumber = markNumber;
    }
}
