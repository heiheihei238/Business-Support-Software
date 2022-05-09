package controller;

import entities.Staff;
import service.StaffService;

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
public class StaffController {

    private Staff staff;

    private static Integer staffId;

    private Integer searchID = 0;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 5;

    private int totalCount;

    @Inject
    private StaffService ss;

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

    public static void setCurrentPage(int currentPage) {
        StaffController.currentPage = currentPage;
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
        StaffController.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSearchID() {
        return searchID;
    }

    public void setSearchID(int searchID) {
        this.searchID = searchID;
    }

    public String search() {
        StaffController.setCurrentPage(1);
        return "/sc/admin/staff.xhtml?searchID=" + searchID + "&faces-redirect=true";
    }

    // pagination
    public List<Staff> getAll() {
        if(searchID != 0) {
            return ss.findAllById(currentPage, pageSize, searchID);
        }
        else{
            return ss.findAll(currentPage, pageSize);
        }
    }

    // update staff
    public String update() {
        staff.setStaffId(staffId);
        ss.update(this.staff);
        return "/sc/admin/editStaff.xhtml?staff_id=" + staffId + "&faces-redirect=true";
    }

    public String save() {
        Logger.getLogger(StaffController.class.getCanonicalName()).info("staff saved: "+ staff);
        ss.save(staff);
        return null;
    }

    public String remove(Staff staff){
        try {
            ss.remove(staff);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

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

    // show details of a staff
    public void initDetails() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("staff_id") != null) {
            staffId = Integer.parseInt(request.getParameter("staff_id"));
            this.staff = ss.find(staffId);
        }
    }

    // edit staff
    public void initEdit() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("staff_id") != null) {
            staffId = Integer.parseInt(request.getParameter("staff_id"));
            this.staff = ss.find(staffId);
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
            return "staff_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
