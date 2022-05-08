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

    // show details of a store
    public void initDetails() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("store_id") != null) {
            storeId = Integer.parseInt(request.getParameter("store_id"));
            this.store = ss.find(storeId);
        }
    }

    // show all store
    public List<Store> getAll() {
        return ss.findAll();
    }

}
