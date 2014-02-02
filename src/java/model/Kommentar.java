package model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Kommentar extends Beitrag {

    @OneToOne
    private Beitrag myBeitrag;

    /**
     * @return the myBeitrag
     */
    public Beitrag getMyBeitrag() {
        return myBeitrag;
    }

    /**
     * @param myBeitrag the myBeitrag to set
     */
    public void setMyBeitrag(Beitrag myBeitrag) {
        this.myBeitrag = myBeitrag;
    }

}
