package dyrhoi.github.jpa_project.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Jørgen", 1998);
        Person p2 = new Person("Marty", 1990);

        Address a1 = new Address("Store Torv 1", 2323, "Søborg");
        Address a2 = new Address("Langgade 334", 4000, "Roskilde");

        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(400);


        p1.setAddress(a1);
        p2.setAddress(a2);

        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);

        em.getTransaction().begin();

//        em.persist(a1);
//        em.persist(a2);
        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();

        System.out.println(p1);
        System.out.println(p2);

        System.out.println("Address bidirectional:");
        System.out.println(a1.getPerson());

        System.out.println("Fee bidirectional:");
        System.out.println(f2.getPerson());

        System.out.println("Hvad har " + p1.getName() + " betalt?");
        p1.getFees().forEach(System.out::println);

        System.out.println("Hvad har alle betalt?");
        TypedQuery<Fee> q = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q.getResultList();
        fees.forEach(System.out::println);

        em.close();
    }
}
