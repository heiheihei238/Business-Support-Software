package controller;


import entities.Customer;
import service.CustomerService;

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
public class CustomerController {

    private Customer customer;

    private static Integer customerId;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 8;

    private int totalCount;

    // data per page
    private List<Customer> customers;

    @Inject
    private CustomerService cs;


    public CustomerController() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        CustomerController.currentPage = currentPage;
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
        CustomerController.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }



    // update customer
    public String update() {
        customer.setCustomerId(customerId);
        cs.update(this.customer);
        return "/sc/admin/editCustomer.xhtml?customer_id=" + customerId + "&faces-redirect=true";
    }

    public String save() {
        Logger.getLogger(CustomerController.class.getCanonicalName()).info("customer saved: "+ customer);
        cs.save(customer);
        return null;
    }

    public String remove(Customer customer){
        try {
            cs.remove(customer);
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("foreign Key violation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public List<Customer> getAllCustomer() {
        return cs.findAll();
    }

    // calculate the number of pages
    public void init() {
        totalCount = cs.findAll().size();
        totalPages = (int) Math.ceil(totalCount / (double) pageSize);
    }

    // show details of a customer
    public void initDetails() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("customer_id") != null) {
            customerId = Integer.parseInt(request.getParameter("customer_id"));
            this.customer = cs.find(customerId);
        }
    }

    // edit customer
    public void initEdit() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("customer_id") != null) {
            customerId = Integer.parseInt(request.getParameter("customer_id"));
            this.customer = cs.find(customerId);
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
            return "customer_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    // data list for pagination
    public List<Customer> getAll() {
        return cs.findAll(currentPage, pageSize);
    }


}
