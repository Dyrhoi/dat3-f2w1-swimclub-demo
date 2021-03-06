package dyrhoi.github.jpa_project.entries;

import dyrhoi.github.jpa_project.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        populate(em);

        Person p1 = new Person("Jørgen", 1998);
        Person p2 = new Person("Marty", 1990);

        Address a1 = new Address("Store Torv 1", 2323, "Søborg");
        Address a2 = new Address("Langgade 334", 4000, "Roskilde");

        Fee f1 = new Fee(200);
        Fee f2 = new Fee(250);
        Fee f3 = new Fee(350);

        SwimStyle s1 = new SwimStyle("crawl");
        SwimStyle s2 = new SwimStyle("butterfly");
        SwimStyle s3 = new SwimStyle("breast stroke");

        Team t1 = new Team("Konkurrence");
        Team t2 = new Team("Begyndere");

        p1.setAddress(a1);
        p2.setAddress(a2);

        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);

        p1.addSwimStyles(s1);
        p1.addSwimStyles(s2);
        p2.addSwimStyles(s1);
        p2.addSwimStyles(s3);

        //p1.addTeam(t1, PersonTeam.SwimmerLevel.HIGH);
        //p1.addTeam(t2, PersonTeam.SwimmerLevel.LOW);
        //p2.addTeam(t2, PersonTeam.SwimmerLevel.MEDIUM);


        em.getTransaction().begin();

//        em.persist(a1);
//        em.persist(a2);
        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();

        em.getTransaction().begin();
        p1.removeSwimStyle(s2);
        em.getTransaction().commit();

        System.out.println(p1);
        System.out.println(p2);

        System.out.println("Address bidirectional:");
        System.out.println(a1.getPerson());

        System.out.println("Fee bidirectional:");
        System.out.println(f2.getPerson());

        System.out.println("Hvad har " + p1.getName() + " betalt?");
        p1.getFees().forEach(System.out::println);

        System.out.println("Pretty print af alle medlemmer og deres betalinger & swimstyles.");
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people = q.getResultList();
        people.forEach(person -> {
            System.out.println("Person:");
            System.out.println(person);
            System.out.println("Regninger:");
            person.getFees().forEach(System.out::println);
            System.out.println("Svømmearter lært: " + person.getSwimStyles().size());
            System.out.println("---");
        });

        System.out.println("Alle medlemmer der kan svømme crawl.");
        q = em.createQuery("SELECT p FROM Person p JOIN p.swimStyles s WHERE s.name = :swimStyleName", Person.class);
        q.setParameter("swimStyleName", "crawl");
        List<Person> peopleCrawl = q.getResultList();

        peopleCrawl.forEach(System.out::println);

        // Lidt botched? Ville være bedre med en enkel JPQL...
        System.out.println("Summen af alle betalte fees");
        int sum = 0;
        for (Person person : people) {
            for (Fee fee : person.getFees()) {
                sum += fee.getAmount();
            }
        }
        System.out.println(sum + " kr.");

        System.out.println("Største og laveste fee.");
        TypedQuery<Integer> qMaxFee = em.createQuery("SELECT MAX(f.amount) FROM Fee f", Integer.class);
        TypedQuery<Integer> qMinFee = em.createQuery("SELECT MIN(f.amount) FROM Fee f", Integer.class);
        int lowest = qMinFee.setMaxResults(1).getSingleResult();
        int highest = qMaxFee.setMaxResults(1).getSingleResult();

        System.out.println(lowest + " - " + highest);



        em.close();
    }

    public static void populate(EntityManager em){

        Person p3 = new Person("Jens", 1961);
        Person p4 = new Person("Ole", 1979);
        Person p5 = new Person("Bente", 1983);
        Person p6 = new Person("Dennis", 1939);
        Person p7 = new Person("Ida", 1990);
        Person p8 = new Person("Mette", 1999);
        Person p9 = new Person("Kaj", 1993);
        Person p10 = new Person("Finn", 2002);
        Person p11 = new Person("Charlotte", 2003);
        Person p12 = new Person("Karin", 1970);
        Person p13 = new Person("Gitte", 1975);
        Person p14 = new Person("Hans", 1989);

        Address a1 = new Address("Storegade 10", 2323, "Nr. Søby");
        Address a2 = new Address("Bredgade 14", 1212, "København K");
        Address a3 = new Address("Lillegade 1", 2323, "Nr. Søby");
        Address a4 = new Address("Damvej", 1212, "København K");
        Address a5 = new Address("Ndr Frihavnsgade 12", 2100, "Kbh Ø");
        Address a6 = new Address("Østerbrogade 85", 1212, "København K");
        Address a7 = new Address("Nørregade 4", 2200, "Nr. Søby");
        Address a8 = new Address("Nørregade 5", 2200, "København K");
        Address a9 = new Address("Odensegade 64", 2323, "Nr. Søby");
        Address a10 = new Address("Århusgade 29", 2300, "København S");

        p3.setAddress(a1);
        p4.setAddress(a2);
        p5.setAddress(a3);
        p6.setAddress(a4);
        p7.setAddress(a5);
        p8.setAddress(a6);
        p9.setAddress(a7);
        p10.setAddress(a8);
        p11.setAddress(a9);
        p12.setAddress(a10);
        p13.setAddress(a1);
        p14.setAddress(a2);


        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);

        p3.addFee(f1);
        p4.addFee(f3);
        p5.addFee(f2);
        p6.addFee(f1);
        p7.addFee(f3);
        p8.addFee(f2);
        p9.addFee(f1);
        p10.addFee(f3);
        p11.addFee(f2);
        p12.addFee(f1);
        p13.addFee(f3);
        p14.addFee(f2);

        em.getTransaction().begin();
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(p6);
        em.persist(p7);
        em.persist(p8);
        em.persist(p9);
        em.persist(p10);
        em.persist(p11);
        em.persist(p12);
        em.persist(p13);
        em.persist(p14);
        em.getTransaction().commit();

    }
}
