package chema.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
//@Transactional(readOnly = true)
public class Servicio {

    @PersistenceContext
    private EntityManager em;

    private static final int MILES = 350;

    public void init() {
        System.out.println("---- inicializando BBDD -----");
        for (int i = 0; i < MILES * 1000; i++) {
            People people = new People();
            people.setName("persona " + i);

            em.persist(people);
        }

        System.out.println("---- inicialización completada -----");
    }

    public List<People> consulta() {
        List<People> resultado = new ArrayList<People>(MILES * 1000);

        TypedQuery<People> query = em.createQuery("select p from People p where p.id >= :inicio and p.id <= :fin", People.class);

        for(int i = 0; i < MILES; i++) {
            query.setParameter("inicio", (i * 1000L) + 1);
            query.setParameter("fin", (i + 1) * 1000L);

            resultado.addAll(query.getResultList());
            //em.clear();
        }

        return resultado;
    }

}
