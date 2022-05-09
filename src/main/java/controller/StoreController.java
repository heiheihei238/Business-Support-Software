package controller;

import entities.Store;
import service.StoreService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Named
@RequestScoped
public class StoreController {

    private Store store;

    private static Integer storeId;

    private Integer searchID = 0;

    @Inject
    private StoreService ss;

    public StoreController() {
        store = new Store();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getSearchID() {
        return searchID;
    }

    public void setSearchID(Integer searchID) {
        this.searchID = searchID;
    }

    public String search(){
        return "/sc/admin/store.xhtml?searchID=" + searchID + "&faces-redirect=true";
    }

    // show details of a store
    public void initDetails() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("store_id") != null) {
            storeId = Integer.parseInt(request.getParameter("store_id"));
            this.store = ss.find(storeId);
        }
    }

    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("searchID") != null) {
            searchID = Integer.parseInt(request.getParameter("searchID"));
        }
    }

    // show all store
    public List<Store> getAll() {
        if(searchID != 0) {
            return ss.findAllById(searchID);
        }
        else{return ss.findAll();}
    }

}
