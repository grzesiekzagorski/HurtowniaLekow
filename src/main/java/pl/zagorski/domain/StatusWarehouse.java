package pl.zagorski.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_warehouse")
public class StatusWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Warehouse> warehouses = new ArrayList<>();



}
