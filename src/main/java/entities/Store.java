package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer store_id;

    private String city;

    private String email;

    private String phone;

    private String state;

    private String store_name;

    private String street;

    private String zip_code;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;

    @OneToMany(mappedBy = "store")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "store")
    private List<Stock> stocks;

    public Store() {
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "Store{" +
                "store_id=" + store_id +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", store_name='" + store_name + '\'' +
                ", street='" + street + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", orders=" + orders +
                ", staffs=" + staffs +
                ", stocks=" + stocks +
                '}';
    }
}
