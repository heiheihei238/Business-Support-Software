package controller;

import entities.Category;
import service.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CategoryController {

    private Category category;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 5;

    private int totalCount;

    @Inject
    private CategoryService cs;

    public CategoryController() {
        category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        CategoryController.currentPage = currentPage;
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
        this.totalCount = getAllCategory().size();
    }


    public List<Category> getAllCategory() {
        return cs.findAll();
    }

    // pagination
    public List<Category> getAll(int page, int pageSize) {
        return cs.findAll(page, pageSize);
    }

    public void init() {
        totalCount = cs.findAll().size();
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

    // add a new category
    public String save() {
        Logger.getLogger(CategoryController.class.getCanonicalName()).info("category saved: "+ category);
        cs.save(category);
        return null;
    }

    // delete a category
    public String remove(Category category){
        try {
            cs.remove(category);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    // show products by category
    public String showProducts(Category category) {
        ProductController.setCurrentPage(1);
        return "/sc/admin/product.xhtml?category_id=" + category.getCategoryId() + "&faces-redirect=true";
    }

}
