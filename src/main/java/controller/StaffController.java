package controller;

import entities.Staff;
import service.StaffService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class StaffController {

    private Staff staff;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 5;

    private int totalCount;

    @Inject
    private StaffService cs;

    public StaffController() {
        staff = new Staff();
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = StaffController.this.staff;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        StaffController.currentPage = currentPage;
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
        this.totalCount = getAllStaff().size();
    }



    // add staff
    public String addStaff() {
        cs.save(staff);
        return "index";
    }

    public List<Staff> getAllStaff() {
        return cs.findAll();
    }

    // pagination
    public List<Staff> getAll(int page, int pageSize) {
        return cs.findAll(page, pageSize);
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
}
