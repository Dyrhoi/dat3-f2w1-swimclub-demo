package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class PersonTeam implements Serializable {
    public enum SwimmerLevel {
        LOW,
        MEDIUM,
        HIGH
    }

    @EmbeddedId
    private PersonTeamId id;

    @ManyToOne
    @MapsId("p_id")
    @JoinColumn(name = "p_id")
    private Person person;

    @ManyToOne
    @MapsId("t_id")
    @JoinColumn(name = "t_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private SwimmerLevel level;

    public PersonTeam() {
    }

    public PersonTeam(Person person, Team team, SwimmerLevel level) {
        this.person = person;
        this.team = team;
        this.level = level;
        this.id = new PersonTeamId(person.getId(), team.getT_id());
    }

    public PersonTeamId getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Team getTeam() {
        return team;
    }

    public SwimmerLevel getLevel() {
        return level;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



    @Override
    public int hashCode() {
        return Objects.hash(person, team);
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
        final PersonTeam other = (PersonTeam) obj;
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonTeam{" + "id=" + id + ", person=" + person + ", team=" + team + ", level=" + level + '}';
    }
}
