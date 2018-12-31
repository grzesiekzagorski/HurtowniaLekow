package pl.zagorski.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;

    private int amount;

    private Date date_of_order;

    @OneToMany
    @JoinColumn(name = "purchaseOrder_id")
    private List<Warehouse> warehouseList;

    @OneToMany
    @JoinColumn(name = "purchaseOrder_id")
    private List<Delivery> deliveryList;

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

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }
}
