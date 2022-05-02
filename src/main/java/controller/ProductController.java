package controller;


import entities.Category;
import entities.Product;
import service.ProductService;

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
public class ProductController {

    private Product product;

    private static Integer categoryId = 0;

    private static Integer brandId = 0;

    //当前页
    private static int currentPage = 1;

    //总页数
    private static int totalPages;

    //每页显示的数量
    private int pageSize = 8;

    //总数量
    private int totalCount;

    //每页的数据
    private List<Product> products;

    @Inject
    ProductService ps;

    public ProductController() {
        product = new Product();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        ProductController.categoryId = categoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(int currentPage) {
        ProductController.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        ProductController.totalPages = totalPages;
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

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    public Product update() {
        return ps.update(this.product);
    }

    public String save() {
        Logger.getLogger(ProductController.class.getCanonicalName()).info("product saved: "+ product);
        ps.save(product);
        return null;
    }

    public String remove(Product product) {
        try {
            ps.remove(product);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public List<Product> getAllProduct() {
        return ps.findAll();
    }

    public void init() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // the page jumped not from brand
        if (request.getParameter("category_id") != null) {
            brandId = 0;
            categoryId = Integer.parseInt(request.getParameter("category_id"));
            totalCount = ps.findAllByCategory(categoryId).size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        }
        // the page jumped not from category
        else if (request.getParameter("brand_id") != null) {
            categoryId = 0;
            brandId = Integer.parseInt(request.getParameter("brand_id"));
            totalCount = ps.findAllByBrand(brandId).size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        }
        // show all products
        else {
            brandId = 0;
            categoryId = 0;
            totalCount = ps.findAll().size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
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
//        setTotalPages();
        currentPage = totalPages;
        return null;
    }

    // pagination
    public List<Product> getAll(int page, int pageSize) {
        if (categoryId == 0 && brandId == 0) return ps.findAll(page, pageSize);
        // the page is jumped from category.
        else if(categoryId != 0) return ps.findAllByCategory(page, pageSize, categoryId);
        // the page is jumped from brand.
        else return ps.findAllByBrand(page, pageSize, brandId);
    }

    // jump to page
    public String go(int page) {
        if (page > 0 && page <= totalPages) {
            currentPage = page;
            return "product_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}