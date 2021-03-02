package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SwimStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<Person> persons;

    public SwimStyle() {
    }

    public SwimStyle(String name) {
        this.name = name;
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
