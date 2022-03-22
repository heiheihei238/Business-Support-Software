package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_kunde")
public class Kunde {
    @GeneratedValue
    @Id
    Long id;

    Date birthday;

    String address;

}
