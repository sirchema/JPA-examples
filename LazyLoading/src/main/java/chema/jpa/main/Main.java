package chema.jpa.main;

import chema.jpa.domain.People;
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
        con.createStatement().executeUpdate("INSERT INTO people (name, hobby) VALUES ('Chema', 'read')");
        con.commit();

        System.out.println("-------- query --------");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Lazy loading
        People people = em.getReference(People.class, "Chema");

        System.out.println(people.getClass().getName());

        //Add 1 Don't go to bbdd
        people.getName();
        //Add 2 Go to bbdd
        people.getHobby();

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
