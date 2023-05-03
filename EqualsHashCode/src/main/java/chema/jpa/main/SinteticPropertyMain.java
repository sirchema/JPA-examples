package chema.jpa.main;

import chema.jpa.domain.People.People;
import chema.jpa.domain.People.PeopleSinteticProperty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;
import java.util.HashSet;

public class SinteticPropertyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");

        Collection<PeopleSinteticProperty> peopleList = new HashSet<>();

        peopleList.add(new PeopleSinteticProperty("primera"));
        peopleList.add(new PeopleSinteticProperty("segunda"));
        peopleList.add(new PeopleSinteticProperty("tercera"));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Only one insert because I have a equals with only Id parameter and Id is autogenerate and until we persist it in bbdd,
        // the value of Id is null, and it's the same value for three object.
        peopleList.stream().forEach(pe -> em.persist(pe));

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
