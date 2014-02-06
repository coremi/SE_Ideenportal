package controller;

import model.Idee;
import controller.util.JsfUtil;
import controller.util.PaginationHelper;
import facades.IdeeFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import model.Bild;
import model.Kommentar;
import model.Mitarbeiter;

@Named("ideeController")
@SessionScoped
public class IdeeController implements Serializable {
    
    @Inject
    private BildController bc;
    @Inject
    private MitarbeiterController mc;  
    private Idee current;
    private DataModel items = null;
    @EJB
    private facades.IdeeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public IdeeController() {
    }
          
    /**
     * adds a vote to the bewertung
     * @param i
     * @return 
     */
    public String voteIdee(int i) {
        current.setBewertung(i);
        current.addBewerter(mc.getSelected());
        return update();
    }
    
    /**
     * checks if the given mitarbeiter has already voted on the current idee
     * @param mitarbeiter
     * @return 
     */
    public boolean alreadyVoted(Mitarbeiter mitarbeiter) {
        for (Mitarbeiter m : current.getBewerter()) {
            if (m.getId() == mitarbeiter.getId()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * adds a given bild to the idee
     * @param bild
     * @return 
     */
    public String saveBild(Bild bild) {
        Bild tmp = new Bild();
        tmp.setUrl(bild.getUrl());
        tmp.setTitel(bild.getTitel());
        current.addBild(tmp);
        bc.newBild();
        return "edit_idea.xhtml?faces-redirect-true";
    }
    
    /**
     * returns true, if the current idee has bilder
     * @return 
     */
    public boolean hasBilder() {
        return !current.getBilder().isEmpty();
    }
    
    /**
     * adds a kommentar to the current idee
     * @param kommentar
     * @return 
     */
    public String addKommentar(Kommentar kommentar) {
        current.addKommentar(kommentar);
        Long tmp = current.getId();
        update();
        current = getIdee(tmp);
        return "idea.xhtml?faces-redirect-true";
    }
    
    /**
     * removes the given bild from the idee
     * BUG: still exists in Bild db
     * @param bild
     * @return 
     */
    public String removeBild(Bild bild) {
        current.removeBild(bild);
        return "";
    }

    /**
     * redirects to the idea.xhtml for the idea with the given id
     * @param id
     * @return 
     */
    public String showIdee(java.lang.Long id) {
        current = ejbFacade.find(id);
        return "idea.xhtml?faces-redirect-true";
    }
    
    /**
     * redirects to the edit page for the current idee
     * @return 
     */
    public String editIdee() {
        return "edit_idea.xhtml?faces-redirect-true";
    }
    
    /**
     * deselects any selected idee and redirects to new_idea.xhtml
     * @return 
     */
    public String newIdee() {
        current = new Idee();
        return "new_idea.xhtml?faces-redirect-true";
    }

    public Idee getSelected() {
        if (current == null) {
            current = new Idee();
            selectedItemIndex = -1;
        }
        return current;
    }
   
    private IdeeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Idee) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    /**
     * redirects to the my_ideas page after creating a new idea
     * @return 
     */
    public String prepareCreate() {
        current = new Idee();
        selectedItemIndex = -1;
        return "my_ideas.xhtml?faces-redirect-true";
    }

    public String create() {
        try {
            //be sure that the logged in mitarbeiter is selected
            mc.loadLoggedMitarbeiter();
            //sets the author of the idea to the logged in mitarbeiter
            current.setMitarbeiter(mc.getSelected());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Idee) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    /**
     * redirects to the idea.xhtml after successfully updating a new idea,
     * adds a timestamp when the update happened
     * @return 
     */
    public String updateWithEditDate() {
        //update editiert value on changes
        current.setEditiert();
        return update();
    }
    
    /**
     * redirects to the idea.xhtml after successfully updating a new idea
     * @return 
     */
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeeUpdated"));
            return "idea.xhtml?faces-redirect-true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * delete the selected idee and redirects to my_ideas.xhtml
     * @return 
     */
    public String destroy() {
        current = (Idee) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "my_ideas.xhtml?faces-redirect-true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Idee getIdee(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    public MitarbeiterController getMc() {
        return mc;
    }

    public void setMc(MitarbeiterController mc) {
        this.mc = mc;
    }

    @FacesConverter(forClass = Idee.class)
    public static class IdeeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdeeController controller = (IdeeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ideeController");
            return controller.getIdee(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Idee) {
                Idee o = (Idee) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Idee.class.getName());
            }
        }

    }

}
