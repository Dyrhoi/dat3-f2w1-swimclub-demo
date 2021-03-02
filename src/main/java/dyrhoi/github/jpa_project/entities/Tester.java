package dyrhoi.github.jpa_project.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Jørgen", 1998);
        Person p2 = new Person("Marty", 1990);

        Address a1 = new Address("Store Torv 1", 2323, "Søborg");
        Address a2 = new Address("Langgade 334", 4000, "Roskilde");

        p1.setAddress(a1);
        p2.setAddress(a2);

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

        em.close();
    }
}
