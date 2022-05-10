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

    private String searchItem = "Search";

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

    public static Integer getStoreId() {
        return storeId;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public String search(){
        return "/sc/admin/store.xhtml?searchItem=" + searchItem + "&faces-redirect=true";
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
        if (request.getParameter("searchItem") != null) {
            searchItem = request.getParameter("searchItem");
        }
        else {
            searchItem = "Search";
        }
    }

    // show all store
    public List<Store> getAll() {
        if(!searchItem.equals("Search")){
            return ss.findAllByIdAndName(searchItem);
        }
        else{return ss.findAll();}
    }

}
