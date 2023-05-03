package chema.jpa.main;

import chema.jpa.domain.Car;
import chema.jpa.domain.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CascadeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");

        People newPeople = new People("Miguel");
        Car newCar = new Car(newPeople);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(newCar);

        em.getTransaction().commit();
        em.close();

        //TX 2
        System.out.println("Transaction 2");
        newCar.setOwner(new People("Antonia"));

        em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(newCar);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
