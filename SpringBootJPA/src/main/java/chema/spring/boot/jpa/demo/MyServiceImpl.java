package chema.spring.boot.jpa.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyServiceImpl {

    @PersistenceContext
    private EntityManager em;

    public MyServiceImpl() {

    }

    public People create (String name, String town) {
        People people = new People();
        people.setName("Chema");
        people.setTown(town);

        em.persist(people);

        return people;
    }

    public People query(String name) {
        return em.getReference(People.class,name);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
