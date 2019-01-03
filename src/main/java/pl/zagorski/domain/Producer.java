package pl.zagorski.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private int id;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "producer")
    private List<Medicine> medicines = new ArrayList<>();

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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
