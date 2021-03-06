package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int zip;
    private String city;

    @OneToOne(mappedBy = "address")
    private Person person;

    public Address() {
    }

    public Address(String street, int zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return street + ". " + city + ", " + zip;
    }
}
