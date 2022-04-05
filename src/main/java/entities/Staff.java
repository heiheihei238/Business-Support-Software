package entities;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staff_id;

    private Integer active;

    private String email;

    private String first_name;

    private String last_name;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private Staff manager;

    private Integer manager_id;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    private Integer store_id;

    public Staff() {
    }

    public Staff(Integer staff_id, Integer active, String email, String first_name, String last_name, String phone, Integer manager_id, Integer store_id) {
        this.staff_id = staff_id;
        this.active = active;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.manager_id = manager_id;
        this.store_id = store_id;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Staffs{" +
                "staff_id=" + staff_id +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", manager=" + manager +
                ", manager_id=" + manager_id +
                ", store=" + store +
                ", store_id=" + store_id +
                '}';
    }
}
