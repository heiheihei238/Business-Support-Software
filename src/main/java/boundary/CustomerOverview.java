package boundary;


import entities.Customer;
import service.CustomerService;

import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CustomerOverview {

    private Customer customer;

    //当前页
    private static int currentPage = 1;

    //总页数
    private static int totalPages;

    //每页显示的数量
    private int pageSize = 10;

    //总数量
    private int totalCount;

    //每页的数据
    private List<Customer> customers;

    @Inject
    private CustomerService cs;


    public CustomerOverview() {
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
        CustomerOverview.currentPage = currentPage;
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
        this.totalCount = getAllCustomer().size();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }



    public Customer update() {
        return cs.update(this.customer);
    }

    public String save() {
        Logger.getLogger(CustomerOverview.class.getCanonicalName()).info("customer saved: "+ customer);
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
            /*System.out.println("=========================================================================================== from Service");
            for (Throwable t = e.getCause(); t != null; t = t.getCause()) {
                System.out.println("reason is " + t.getClass().toString());
                if (t.getClass().equals(PSQLException.class)){
                    System.out.println("reason is PSQL");
                }
                if (t.getClass().equals(ConstraintViolationException.class)){
                    System.out.println("reason is CVException");
                }
                if (t.getClass().equals(PersistenceException.class)){
                    System.out.println("reason is PersistenceException");
                }
                if (PSQLException.class.equals(t.getClass())) {
                    PSQLException postgresException = (PSQLException) t;

                    // In Postgres SQLState 23505=unique_violation
                    if ("23505".equals(postgresException.getSQLState())) {
                        System.out.println("===========================================================================================");
                    }
                }

            }*/
        }
    }

    public List<Customer> getAllCustomer() {
        return cs.findAll();
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
    public List<Customer> getAll(int page, int pageSize) {
        return cs.findAll(page, pageSize);
    }

    // 页码跳转
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
