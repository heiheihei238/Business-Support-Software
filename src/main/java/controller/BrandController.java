package controller;

import entities.Brand;
import entities.Category;
import service.BrandService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
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
        this.brand = BrandController.this.brand;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        BrandController.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        setTotalCount();
        totalPages = (int) Math.ceil(getTotalCount() / (double) pageSize);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount() {
        this.totalCount = getAllBrand().size();
    }



    // add brand
    public String save() {
        bs.save(brand);
        return null;
    }

    public List<Brand> getAllBrand() {
        return bs.findAll();
    }

    // pagination
    public List<Brand> getAll(int page, int pageSize) {
        return bs.findAll(page, pageSize);
    }

    public void init() {
        setTotalPages();
    }

    public void initEdit() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("brand_id") != null) {
            int brand_id = Integer.parseInt(request.getParameter("brand_id"));
            this.brand = bs.find(brand_id);
            this.brand.setBrand_id(brand_id);
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
        setTotalPages();
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

    // update brand
    public String update() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("brand_id") != null) {
            int brand_id = Integer.parseInt(request.getParameter("brand_id"));
            this.brand = bs.find(brand_id);
            this.brand.setBrand_id(brand_id);
        }
        System.out.println(brand);
        bs.update(this.brand);
        return null;
    }

    // show products by category
    public String showProducts(Brand brand) {
        ProductController.setCurrentPage(1);
        return "/sc/admin/product.xhtml?brand_id=" + brand.getBrand_id() + "&faces-redirect=true";
    }
}
