package pl.zagorski.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private int id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String surname;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL",unique = true)
    private String login;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String password;

    @OneToOne
    @JoinColumn
    private Position position;

    public int getId() {
        return id;
    }

    @OneToMany
    @JoinColumn(name = "purchaser_id")
    private List<PurchaseOrder> orders;

    @OneToMany
    @JoinColumn(name = "accepting_delivery_id")
    private List<Warehouse> warehouseList;

    @OneToMany
    @JoinColumn(name = "accepting_delivery_id")
    private List<Delivery> deliveryList;

    @OneToMany
    @JoinColumn(name = "seller_id")
    private List<Sale> sales;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<PurchaseOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PurchaseOrder> orders) {
        this.orders = orders;
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

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
