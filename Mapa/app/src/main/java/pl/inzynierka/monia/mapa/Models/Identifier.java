package pl.inzynierka.monia.mapa.Models;

import io.realm.RealmObject;

public class Identifier extends RealmObject {

    private int id;

    private String name;
    private char markLetter;
    private int markNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getMarkLetter() {
        return markLetter;
    }

    public void setMarkLetter(char markLetter) {
        this.markLetter = markLetter;
    }

    public int getMarkNumber() {
        return markNumber;
    }

    public void setMarkNumber(int markNumber) {
        this.markNumber = markNumber;
    }
}
