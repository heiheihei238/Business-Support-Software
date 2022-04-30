package controller;


import entities.Order;
import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class OrderController {

    private Order order;

    private static int currentPage = 1;

    private static int totalPages;

    // data amount in one page
    private int pageSize = 10;

    private int totalCount;

    // Data per page
    private List<Order> orders;

    @Inject
    private OrderService os;


    public OrderController() {
        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        OrderController.currentPage = currentPage;
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
        this.totalCount = getAllOrder().size();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }



    public Order update() {
        return os.update(this.order);
    }

    public String save() {
        Logger.getLogger(OrderController.class.getCanonicalName()).info("order saved: "+ order);
        os.save(order);
        return null;
    }

    public String remove(Order order){
        try {
            os.remove(order);
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

    public void init(){
        setTotalPages();
    }

    public List<Order> getAllOrder() {
        return os.findAll();
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

    // data list for pagination
    public List<Order> getAll(int page, int pageSize) {
        return os.findAll(page, pageSize);
    }

    // jump to page
    public String go(int page) {
        if (page > 0 && page <= totalPages) {
            currentPage = page;
            return "order_list";
        } else {
            FacesMessage msg = new FacesMessage("invalid page number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
