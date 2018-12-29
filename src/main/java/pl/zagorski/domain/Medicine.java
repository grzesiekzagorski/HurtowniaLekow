package pl.zagorski.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;

    private double price;

    private double discount;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String wrapping;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String portion;

    @OneToMany
    @JoinColumn(name = "medicine_id")
    private List<PurchaseOrder> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getWrapping() {
        return wrapping;
    }

    public void setWrapping(String wrapping) {
        this.wrapping = wrapping;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public List<PurchaseOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PurchaseOrder> orders) {
        this.orders = orders;
    }
}
