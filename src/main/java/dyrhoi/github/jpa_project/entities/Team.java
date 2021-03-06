package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long t_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonTeam> persons;

    public List<PersonTeam> getPersons() {
        return persons;
    }

    public Team() {
    }

    public Team(String name) {
        this.name = name;
        persons = new ArrayList<>();
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersons(List<PersonTeam> persons) {
        this.persons = persons;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.t_id, other.t_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" + "t_id=" + t_id + ", name=" + name + '}';
    }

}
