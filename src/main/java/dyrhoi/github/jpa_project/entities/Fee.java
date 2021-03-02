package dyrhoi.github.jpa_project.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    @Temporal(TemporalType.DATE)
    private Date payDate;

    @ManyToOne
    private Person person;

    public Fee() {
    }

    public Fee(int amount) {
        this.amount = amount;
        this.payDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "Person-name" + person.getName() + ": " +
                "id=" + id +
                ", amount=" + amount +
                ", payDate=" + payDate +
                '}';
    }
}
