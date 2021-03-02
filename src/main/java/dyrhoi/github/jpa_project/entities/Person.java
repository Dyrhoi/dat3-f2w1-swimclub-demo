package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int year;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "person",cascade = CascadeType.PERSIST)
    private List<Fee> fees;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
        this.fees = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if(address != null) address.setPerson(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void addFee(Fee fee) {
        this.fees.add(fee);
        if(fee != null) fee.setPerson(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", address=" + address +
                '}';
    }
}
