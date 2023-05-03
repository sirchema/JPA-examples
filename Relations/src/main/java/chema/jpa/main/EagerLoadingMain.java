package chema.jpa.main;

import chema.jpa.domain.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EagerLoadingMain {
    public static void main(String[] args) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_main");
        Connection con = DriverManager.getConnection("jdbc:h2:mem:test");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('grandfather',null)");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('father',1)");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('me',2)");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('son',3)");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('grandson',4)");
        con.createStatement().executeUpdate("INSERT INTO people (name, father_id) values ('great-grandson',5)");
        con.commit();

        EntityManager em = emf.createEntityManager();
        People people = em.find(People.class,6l);//3 select

        em.close();
        emf.close();

        while(people != null) {
            System.out.println(people.getName());
            people = people.getFather();
        }
    }
}
