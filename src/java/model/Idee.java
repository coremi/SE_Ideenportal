package model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Idee extends Beitrag {

    private Boolean status;

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    /**
     *
     * @element-type Anhang
     */
    @OneToMany
    private Collection<Anhang> anhaenge;

    /**
     * @return the anhaenge
     */
    public Collection<Anhang> getAnhaenge() {
        return anhaenge;
    }

    /**
     * @param anhaenge the anhaenge to set
     */
    public void setAnhaenge(Collection<Anhang> anhaenge) {
        this.anhaenge = anhaenge;
    }
}
