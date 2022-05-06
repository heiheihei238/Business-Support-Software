package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "stores")
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private int storeId;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "store_name")
    private String storeName;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "zip_code")
    private String zipCode;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeId == store.storeId && Objects.equals(city, store.city) && Objects.equals(email, store.email) && Objects.equals(phone, store.phone) && Objects.equals(state, store.state) && Objects.equals(storeName, store.storeName) && Objects.equals(street, store.street) && Objects.equals(zipCode, store.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, city, email, phone, state, storeName, street, zipCode);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", storeName='" + storeName + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'';
    }

}
