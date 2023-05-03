package chema.jpa.main;

import chema.jpa.domain.Car;
import chema.jpa.domain.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");

        People chema = new People();
        chema.setName("Chema");

        Car car = new Car();
        car.setOwner(chema);
        car.setModel("Panda");

        People sara = new People();
        sara.setName("Sara");

        sara.getCarsSet().add(car);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(chema);
        em.persist(car);
        em.persist(sara);

        /*
        em.refresh(car);
        em.refresh(sara);
        em.refresh(chema);
         */

        System.out.println("Owner " + car.getOwner().getName());
        System.out.println("How many cars does Sara? " + sara.getCarsSet().size());
        System.out.println("How many cars does Chema? " + chema.getCarsSet().size());

        em.getTransaction().commit();
        em.close();

        emf.close();
    }
}
