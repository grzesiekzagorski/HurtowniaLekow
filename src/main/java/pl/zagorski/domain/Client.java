package pl.zagorski.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private int id;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    @Column(columnDefinition = "CHAR(9) NOT NULL")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String city;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String street;

    @Column(columnDefinition = "CHAR(10) NOT NULL")
    private String house_number;

    @Column(columnDefinition = "CHAR(6) NOT NULL")
    private String postal_code;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Sale> sales;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
