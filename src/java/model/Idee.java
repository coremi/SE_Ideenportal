package model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Idee extends Beitrag {

    private Boolean status;

    /**
     *
     * @element-type Anhang
     */
    @OneToMany
    private Collection<Anhang> anhaenge;    
    
    public Idee() {
        this.anhaenge = new ArrayList<Anhang>();
    }
    
    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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
    
    public void addAnhang(Anhang anhang) {
        this.getAnhaenge().add(anhang);
    }
    
    public void removeAnhang(Anhang anhang) {
        this.getAnhaenge().remove(anhang);
    }    
}
