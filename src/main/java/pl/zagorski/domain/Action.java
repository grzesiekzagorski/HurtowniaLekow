package pl.zagorski.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = Action.ORDER_BY_NAME, query = "Select c from Action c order by name asc")
})
public class Action {

    public static final String ORDER_BY_NAME = "orderByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @ManyToMany
    private List<Position> positions;

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

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }


}
