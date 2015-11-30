package pl.inzynierka.monia.mapa.Models;

import io.realm.RealmObject;

public class Faculty extends RealmObject {

    private Identifier identifier;
    private Contact contact;

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
