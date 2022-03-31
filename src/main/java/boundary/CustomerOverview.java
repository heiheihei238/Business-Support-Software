package boundary;


import entities.Customer;
import service.CustomerService;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CustomerOverview {

    private Customer customer;

    @Inject
    CustomerService cs;

    public CustomerOverview() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer update() {
        return cs.update(this.customer);
    }

    public String save() {
        Logger.getLogger(CustomerOverview.class.getCanonicalName()).info("customer saved: "+ customer);
        cs.save(customer);
        return null;
    }

    public void remove(Customer customer) {
        cs.remove(customer);
    }

    public Customer find(Long customer_id) {
        return cs.find(customer_id);
    }

    public List<Customer> getAll() {
        return cs.all();
    }




}
