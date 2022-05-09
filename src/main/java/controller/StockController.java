package controller;


import entities.Stock;
import service.StockService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class StockController {

    private Stock stock;

    private static Integer productId;

    private static Integer storeId;

    private Integer searchID = 0;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 8;

    private int totalCount;

    // data per page
    private List<Stock> stocks;

    @Inject
    private StockService ss;


    public StockController() {
        stock = new Stock();
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(int currentPage) {
        StockController.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static int getTotalPages() {
        return totalPages;
    }

    public static void setTotalPages(int totalPages) {
        StockController.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Integer getSearchID() {
        return searchID;
    }

    public void setSearchID(Integer searchID) {
        this.searchID = searchID;
    }

    // calculate the number of pages
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("searchID") != null) {
            searchID = Integer.parseInt(request.getParameter("searchID"));
            totalCount = ss.findAllById(searchID).size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        } else {
            totalCount = ss.findAll().size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        }
    }

    // edit stock
    public void initEdit() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("product_id") != null && request.getParameter("store_id") != null) {
            productId = Integer.parseInt(request.getParameter("product_id"));
            storeId = Integer.parseInt(request.getParameter("store_id"));
            this.stock = ss.findByProductIdAndStoreId(productId, storeId);
        }
    }

    public String search() {
        StockController.setCurrentPage(1);
        return "/sc/admin/stock.xhtml?searchID=" + searchID + "&faces-redirect=true";
    }

    // update stock
    public String update() {
        stock.setProductId(productId);
        stock.setStoreId(storeId);
        ss.update(this.stock);
        return "/sc/admin/editStock.xhtml?store_id=" + storeId + "&product_id=" + productId + "&faces-redirect=true";
    }

    // add stock
    public String save() {
        Logger.getLogger(StockController.class.getCanonicalName()).info("stock saved: "+ stock);
        ss.save(stock);
        return null;
    }

    public String remove(Stock stock){
        try {
            ss.remove(stock);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    // data list for pagination
    public List<Stock> getAll() {
        if(searchID != 0) {
            return ss.findAllById(currentPage, pageSize, searchID);
        }
        else{
            return ss.findAll(currentPage, pageSize);
        }
    }

    public String next() {
        if (currentPage < totalPages) {
            currentPage++;
        }
        return null;
    }

    public String previous() {
        if (currentPage > 1) {
            currentPage--;
        }
        return null;
    }

    public String first() {
        currentPage = 1;
        return null;
    }

    public String last() {
        currentPage = totalPages;
        return null;
    }

    // jump to page
    public String go(int page) {
        if (page > 0 && page <= totalPages) {
            currentPage = page;
            return "stock_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
