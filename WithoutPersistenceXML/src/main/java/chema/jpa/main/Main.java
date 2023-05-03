package chema.jpa.main;

import chema.jpa.domain.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PersistenceProvider hpp = new HibernatePersistenceProvider();

        PersistenceUnitInfo persistenceInfo = new SimplePersistenceInfo("no-main");

        Map<String, String> properties = new HashMap<>();

        properties.put("javax.persistence.jdbc.url","jdbc:h2:mem:test");
        properties.put("javax.persistence.schema-generation.database.action", "create");
        properties.put("hibernate.show_sql", "true");

        EntityManagerFactory emf = hpp.createContainerEntityManagerFactory(persistenceInfo, properties);

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
