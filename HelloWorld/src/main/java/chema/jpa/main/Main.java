package chema.jpa.main;

import chema.jpa.domain.People;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        People people = new People();
        people.setName("Chema");

        em.persist(people);
        em.getTransaction().commit();
        em.close();

        emf.close();
    }
}
