package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToMany(mappedBy = "mitarbeiter")
    private List<Beitrag> beitraege;

    private String Name;
    
    private String Vorname;
    
    private String Email;

    private String Abteilung;

    private Integer Personalnr;
    
    private Integer UserId;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    /**
     * @return the beitraege
     */
    public List<Beitrag> getBeitraege() {
        return beitraege;
    }

    /**
     * @param beitraege the beitraege to set
     */
    public void setBeitraege(List<Beitrag> beitraege) {
        this.beitraege = beitraege;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Abteilung
     */
    public String getAbteilung() {
        return Abteilung;
    }

    /**
     * @param Abteilung the Abteilung to set
     */
    public void setAbteilung(String Abteilung) {
        this.Abteilung = Abteilung;
    }

    /**
     * @return the Personalnr
     */
    public Integer getPersonalnr() {
        return Personalnr;
    }

    /**
     * @param Personalnr the Personalnr to set
     */
    public void setPersonalnr(Integer Personalnr) {
        this.Personalnr = Personalnr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mitarbeiter other = (Mitarbeiter) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" + "Name=" + Name + '}';
    }

}
