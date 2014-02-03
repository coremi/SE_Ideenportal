/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Bild;

/**
 *
 * @author kdm86
 */
@Stateless
public class BildFacade extends AbstractFacade<Bild> {
    @PersistenceContext(unitName = "LightsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BildFacade() {
        super(Bild.class);
    }
    
}
