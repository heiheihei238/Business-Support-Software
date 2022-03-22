package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_bestellung")
public class Bestellung {

    @Id
    @GeneratedValue
    private Long id;

    private Date time;

    @OneToOne
    private Product product;

    private Long amount;

    @OneToOne
    private Kunde kunde;






}
