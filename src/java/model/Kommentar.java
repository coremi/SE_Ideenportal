package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Kommentar extends Beitrag {
    @ManyToOne
    private Beitrag beitrag;

    public Beitrag getBeitrag() {
        return beitrag;
    }

    public void setBeitrag(Beitrag beitrag) {
        this.beitrag = beitrag;
    }
}
