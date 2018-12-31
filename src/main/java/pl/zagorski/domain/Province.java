package pl.zagorski.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private int id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @OneToMany
    @JoinColumn(name = "province_id")
    private List<Supplier> suppliers;

    @OneToMany
    @JoinColumn(name = "province_id")
    private List<Client> clients ;

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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
