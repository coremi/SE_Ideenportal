package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@ManagedBean(name = "mitarbeiterBean")
@SessionScoped
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToMany(mappedBy = "mitarbeiter")
    private List<Beitrag> beitraege;

    private String name;
    
    private String vorname;
    
    private String email;

    private String abteilung;

    private Integer personalnr;
    
    private Integer userId;

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
        return userId;
    }

    public void setUserId(Integer UserId) {
        this.userId = UserId;
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
        return name;
    }

    /**
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the Abteilung
     */
    public String getAbteilung() {
        return abteilung;
    }

    /**
     * @param Abteilung the Abteilung to set
     */
    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    /**
     * @return the Personalnr
     */
    public Integer getPersonalnr() {
        return personalnr;
    }

    /**
     * @param Personalnr the Personalnr to set
     */
    public void setPersonalnr(Integer personalnr) {
        this.personalnr = personalnr;
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
        return "Mitarbeiter{" + "Name=" + name + '}';
    }

}
