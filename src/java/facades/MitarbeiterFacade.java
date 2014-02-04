/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import model.Mitarbeiter;

/**
 *
 * @author kdm86
 */
@Stateless
public class MitarbeiterFacade extends AbstractFacade<Mitarbeiter> {
    @PersistenceContext(unitName = "LightsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MitarbeiterFacade() {
        super(Mitarbeiter.class);
    }
 
    public Mitarbeiter getMitarbeiter(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Mitarbeiter> root = cq.from(Mitarbeiter.class);
        cq.select(root);
        //sql query where username == username
        cq.where(cb.equal(root.get("username"), username));
        //liste mit ergebnissen
        List<Mitarbeiter> list = em.createQuery(cq).getResultList();
        if (list.isEmpty()) { 
            return null;
        } else {
            return list.get(0);
        }      
    }
    
}
