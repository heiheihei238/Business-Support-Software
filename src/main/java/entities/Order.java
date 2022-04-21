package entities;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

//    @Temporal(TemporalType.DATE)
    private Date order_date;

    private Integer order_status;

//    @Temporal(TemporalType.DATE)
    private Date required_date;

//    @Temporal(TemporalType.DATE)
    private Date shipped_date;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    private Integer customer_id;

    @ManyToOne
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private Staff staff;

    private Integer staff_id;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    private Integer store_id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_items;

    public Order() {
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Date getRequired_date() {
        return required_date;
    }

    public void setRequired_date(Date required_date) {
        this.required_date = required_date;
    }

    public Date getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(Date shipped_date) {
        this.shipped_date = shipped_date;
    }

    public Customer getCustomers() {
        return customer;
    }

    public void setCustomers(Customer customer) {
        this.customer = customer;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Store getStores() {
        return store;
    }

    public void setStores(Store store) {
        this.store = store;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

//    public List<OrderItem> getOrder_items() {
//        return order_items;
//    }
//
//    public void setOrder_items(List<OrderItem> order_items) {
//        this.order_items = order_items;
//    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", order_date=" + order_date +
                ", order_status=" + order_status +
                ", required_date=" + required_date +
                ", shipped_date=" + shipped_date +
                ", customers=" + customer +
                ", customer_id=" + customer_id +
                ", staffs=" + staff +
                ", staff_id=" + staff_id +
                ", stores=" + store +
                ", store_id=" + store_id +
//                ", order_items=" + order_items +
                '}';
    }
}
