package pl.zagorski.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id",unique = true)
    private int positionId;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @ManyToMany
    private List<Action> actions;

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }


}
