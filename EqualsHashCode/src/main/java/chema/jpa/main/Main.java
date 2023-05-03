package chema.jpa.main;

import chema.jpa.domain.People.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");

        //Initializing via JDBC
        Connection con = DriverManager.getConnection("jdbc:h2:mem:test");
        con.createStatement().executeUpdate("INSERT INTO people (name) VALUES ('Chema')");
        con.commit();

        People firstPeople = new People();
        firstPeople.setName("Chema");

        System.out.println("-------- query --------");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        People secondPeople = em.find(People.class,"Chema");

        System.out.println("first.equals(second)? " + firstPeople.equals(secondPeople));
        System.out.println("second.equals(first)? " + secondPeople.equals(firstPeople));

        System.out.println("-------- Lazy --------");
        //Lazy loading
        secondPeople = em.getReference(People.class, "Chema");

        System.out.println("first.equals(second)? " + firstPeople.equals(secondPeople));
        System.out.println("second.equals(first)? " + secondPeople.equals(firstPeople));

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

