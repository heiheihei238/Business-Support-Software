package controller;


import entities.Order;
import service.OrderService;

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
public class OrderController {

    private Order order;

    private static int currentPage = 1;

    private static int totalPages;

    private Integer searchID = 0;

    // data amount in one page
    private int pageSize = 10;

    private int totalCount;

    private static Integer orderId;


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

    public static void setCurrentPage(int currentPage) {
        OrderController.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
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

    public void setTotalPages() {
        setTotalCount();
        totalPages = (int) Math.ceil(getTotalCount() / (double) pageSize);
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

    public Integer getSearchID() {
        return searchID;
    }

    public void setSearchID(Integer searchID) {
        this.searchID = searchID;
    }

    public String search(){
        OrderController.setCurrentPage(1);
        return "/sc/admin/oder.xhtml?searchID=" + searchID + "&faces-redirect=true";
    }

    public String update() {
        order.setOrderId(orderId);
        os.update(this.order);
        return "/sc/admin/editOrder.xhtml?order_id=" + orderId + "&faces-redirect=true";
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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("searchID") != null) {
            searchID = Integer.parseInt(request.getParameter("searchID"));
            totalCount = os.findAllById(searchID).size();
            totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        } else {
            setTotalPages();
        }
    }

    public void initEdit() {
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter("order_id") != null) {
            orderId = Integer.parseInt(request.getParameter("order_id"));
            this.order = os.find(orderId);
            System.out.println("GET"+orderId);
            System.out.println(this.order.toString());
        }
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
    public List<Order> getAll() {
        if(searchID != 0) {
            return os.findAllById(currentPage, pageSize, searchID);
        }
        else{
            return os.findAll(currentPage, pageSize);
        }
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
