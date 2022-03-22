package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue
    Long id;

    Double price;

    Long amount;
}
