/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.OneToMany;

/**
 *
 * @author kdm86
 */
@ManagedBean
@SessionScoped
public class BeitragListe {


    
    @OneToMany(mappedBy = "beitragListe")
    private List<Beitrag> beitraege;
    
    /**
     * Creates a new instance of BeitragListe
     */
    public BeitragListe() {
    }

    public List<Beitrag> getBeitraege() {
        return beitraege;
    }

    public void setBeitraege(List<Beitrag> alleBeitraege) {
        this.beitraege = alleBeitraege;
    }
    
    /**
     * adds a single beitrag to the list
     * @param beitrag 
     */
    public void addBeitrag(Beitrag beitrag) {
        beitraege.add(beitrag);
    }
    
    /**
     * returns a beitragsList for a given mitarbeiter
     * @param mitarbeiter
     * @return 
     */
    public List<Beitrag> getMitarbeiterBeitraege(Mitarbeiter mitarbeiter) {
        List<Beitrag> temp = new ArrayList<Beitrag>();
        for (Beitrag beitrag: beitraege) {
            if (mitarbeiter == beitrag.getMitarbeiter()) {
                temp.add(beitrag);
            }
        }
        return temp;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.beitraege);
        return hash;
    }

    @Override
    public String toString() {
        return "BeitragListe{" + "beitraege=" + beitraege + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeitragListe other = (BeitragListe) obj;
        if (!Objects.equals(this.beitraege, other.beitraege)) {
            return false;
        }
        return true;
    }    
}
