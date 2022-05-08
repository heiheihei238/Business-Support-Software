package controller;

import entities.Brand;
import service.BrandService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class BrandController {

    private Brand brand;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 5;

    private int totalCount;

    @Inject
    private BrandService bs;

    public BrandController() {
        brand = new Brand();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        BrandController.currentPage = currentPage;
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
        BrandController.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    // add brand
    public String save() {
        bs.save(brand);
        return null;
    }

    // pagination
    public List<Brand> getAll(int page, int pageSize) {
        return bs.findAll(page, pageSize);
    }

    public void init() {
        totalCount = bs.findAll().size();
        totalPages = (int) Math.ceil(totalCount / (double) pageSize);
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
            return "customer_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String remove(Brand brand){
        try {
            bs.remove(brand);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    // show products by category
    public String showProducts(Brand brand) {
        ProductController.setCurrentPage(1);
        return "/sc/admin/product.xhtml?brand_id=" + brand.getBrandId() + "&faces-redirect=true";
    }
}
