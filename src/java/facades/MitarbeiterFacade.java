/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
