package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Beitrag implements Serializable {
    @ManyToMany
    private Collection<Mitarbeiter> bewerter;
    
    @ManyToOne
    private Mitarbeiter mitarbeiter;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private final Date datum;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date editiert;

    private Integer bewertung;

    private String titel;

    private String beschreibung;
    
    @OneToMany(mappedBy = "beitrag")
    private Collection<Kommentar> kommentare;

    /**
     *
     * @element-type Bild
     */
    @OneToMany
    private Collection<Bild> bilder;

    public Beitrag() {
        this.datum = new Date();
        this.bewertung = 0;
        this.bilder= new ArrayList<Bild>();
    }
    
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

    /**
     * @return the datum
     */
    public Date getDatum() {
        return datum;
    }

    public Date getEditiert() {
        return editiert;
    }

    public void setEditiert() {
        this.editiert = new Date();
    }

    /**
     * @return the bewertung
     */
    public Integer getBewertung() {
        return bewertung;
    }

    /**
     * @param bewertung the bewertung to set
     */
    public void setBewertung(Integer bewertung) {
        this.bewertung += bewertung;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    /**
     * @return the titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * @param titel the titel to set
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * @return the beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * @param beschreibung the beschreibung to set
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * @return the bilder
     */
    public Collection<Bild> getBilder() {
        return bilder;
    }

    /**
     * @param bilder the bilder to set
     */
    public void setBilder(Collection<Bild> bilder) {
        this.bilder = bilder;
    }
    
    public void addBild(Bild bild) {
        this.bilder.add(bild);
    }
    
    public void removeBild(Bild bild) {
        this.bilder.remove(bild);
    }

    /**
     *
     * @return 
     * @element-type Kommentar
     */
    @Override
    public String toString() {
        return "Beitrag{" + "titel=" + titel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Beitrag other = (Beitrag) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Collection<Mitarbeiter> getBewerter() {
        return bewerter;
    }

    public void setBewerter(Collection<Mitarbeiter> bewerter) {
        this.bewerter = bewerter;
    }

    public Collection<Kommentar> getKommentare() {
        return kommentare;
    }

    public void setKommentare(Collection<Kommentar> kommentare) {
        this.kommentare = kommentare;
    }


}
