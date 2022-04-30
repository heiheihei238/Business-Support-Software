package controller;


import entities.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductController {

    private Product product;

    //当前页
    private static int currentPage = 1;

    //总页数
    private static int totalPages;

    //每页显示的数量
    private int pageSize = 10;

    //总数量
    private int totalCount;

    //每页的数据
    private List<Product> products;

    @Inject
    ProductService ps;

    public ProductController() {
        product = new Product();
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

    public void setCurrentPage(int currentPage) {
        ProductController.currentPage = currentPage;
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
        this.totalCount = getAllProduct().size();
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
        setTotalPages();
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

    // 分页展示
    public List<Product> getAll(int page, int pageSize) {
        return ps.findAll(page, pageSize);
    }

    // 页码跳转
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