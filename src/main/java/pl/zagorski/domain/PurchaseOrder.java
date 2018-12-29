package pl.zagorski.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;

    private int amount;

    private Date date_of_order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }
}
