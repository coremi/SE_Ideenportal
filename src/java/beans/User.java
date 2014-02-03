/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Mitarbeiter;

/**
 *
 * @author corina
 */
@ManagedBean(name = "User")
@SessionScoped
public class User {
    String username;
    Mitarbeiter mitarbeiter;
    
    /**
     * Creates a new instance of User
     */
    public User() {
        this.username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
        // get mitarbeiter with username=username
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void logout() {
        
    }
}
