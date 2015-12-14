package pl.inzynierka.monia.mapa.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Faculty extends RealmObject {

    @PrimaryKey
    private int id;

    private Identifier identifier;
    private Contact contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
