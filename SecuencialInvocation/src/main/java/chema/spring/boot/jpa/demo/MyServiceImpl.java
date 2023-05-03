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

    public People query (long id) {
        return em.find(People.class, id);
    }

    public void clear() {
        em.clear();
    }
}
