/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import controller.MitarbeiterController;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import model.Mitarbeiter;

/**
 *
 * @author corina
 */
@ManagedBean(name = "User")
@SessionScoped
public class User implements Serializable {
    String username;
    Mitarbeiter mitarbeiter;
    
    @ManagedProperty("#{mitarbeiterController}")
    private MitarbeiterController mc;
    
    /**
     * Creates a new instance of User
     */
    public User() {
        this.username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        setMitarbeiter();
    }
    
    private void setMitarbeiter() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LightsPU");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Mitarbeiter> root = cq.from(Mitarbeiter.class);
        cq.select(root);
        //sql query where username == username
        cq.where(cb.equal(root.get("username"), this.username));
        //liste mit ergebnissen
        List<Mitarbeiter> list = em.createQuery(cq).getResultList();
        if (list.isEmpty()) { 
            //hier muss der neue mitarbeiter iwie mit controller/facade/was weiß ich verbunden werden
            //hab hier wild mit MAController.create() rumprobiert, hat aber alles nicht geklappt iwie
            //System.out.println("User: " + mc.getSelected().getUsername());
            Mitarbeiter tmp = mc.getSelected();
            tmp.setUsername(this.username);
            mc.create();
            // weiß nicht, wie man den MA wieder rausbekommt um this.ma auf den zu setzen. weil nach dem create das getselected ein neues leeres element ist
        } else {
            //setzt mitarbeiter auf die daten aus der db
            this.mitarbeiter = list.get(0);
        }      
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public Mitarbeiter getMitarbeiter() {
        return this.mitarbeiter;
    }
    
    public void logout() {
        
    }

    public MitarbeiterController getMc() {
        return mc;
    }

    public void setMc(MitarbeiterController mc) {
        this.mc = mc;
    }
}
