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

    public Customer find(Long customer_id) {
        return cs.find(customer_id);
    }

    public List<Customer> getAll() {
        return cs.findAll();
    }




}
