package chema.jpa.main;

import chema.jpa.domain.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws SQLException {
        emf = Persistence.createEntityManagerFactory("jpa_main");

        Connection con = DriverManager.getConnection("jdbc:h2:mem:test");
        con.createStatement().executeUpdate("INSERT INTO people (name, dni) values ('Chema','12345A')");
        con.createStatement().executeUpdate("INSERT INTO people (name, dni) values ('Sara','456789A')");
        con.commit();

        //Entity Manager <-> Persistence Context <-> State (Managed) <-> Broadcast changes <-> flush <-> Transaction
        try {
            //modifiyManagedWithTransaction();
            //modifyManagedWithoutTransaction();
            //modifyManagedAfterTransaction();
            //explicitFlushWithoutTransaction();
            //OrderOperationSQLInFlush();
            //FlushBeforeQueries();
            OrderOperationAndIntegrityError();
        } finally {
            emf.close();
        }
    }

    public static void modifiyManagedWithTransaction() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        People people = em.find(People.class, "Chema");
        people.setTown("Sevilla");

        //Produce operación flush
        em.getTransaction().commit();

        em.close();
    }

    public static void modifyManagedWithoutTransaction() {
        EntityManager em = emf.createEntityManager();

        People people = em.find(People.class, "Chema");
        people.setTown("Sevilla");

        em.close();
    }

    public static void modifyManagedAfterTransaction() {
        EntityManager em = emf.createEntityManager();

        People people = em.find(People.class, "Chema");
        people.setTown("Sevilla");

        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
    }

    public static void explicitFlushWithoutTransaction() {
        EntityManager em = emf.createEntityManager();

        People people = em.find(People.class, "Chema");
        people.setTown("Sevilla");

        em.flush();
        em.close();
    }

    public static void OrderOperationSQLInFlush() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.remove(em.find(People.class, "Sara"));

        //Garantiza que se ejecuta primero el delete
        //em.flush();

        em.persist(new People("Gaspar"));

        People people = em.find(People.class, "Chema");
        people.setTown("Sevilla");

        em.getTransaction().commit();
        em.close();
    }

    public static void FlushBeforeQueries() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(new People("Pablo"));

        em.createQuery("select p from People p").getResultList();

        em.getTransaction().commit();

        em.close();
    }

    public static void OrderOperationAndIntegrityError() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        People people = em.find(People.class, "Chema");
        em.remove(people);

        //em.flush();

        People otherPeople = new People("Vega");
        otherPeople.setDni(people.getDni());

        em.persist(otherPeople);

        em.getTransaction().commit();
        em.close();
    }
}
