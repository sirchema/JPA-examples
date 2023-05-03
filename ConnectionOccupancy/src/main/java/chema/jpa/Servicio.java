package chema.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Servicio {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WebService ws;

    /*
    public Persona consultaAntecendentes (long id) {
        Persona persona = em.find(Persona.class, id);

        //no es correcto llamar en este método transactional al ws ya que puede tardar mucho y nos deja ocupado el
        //EntityManager
        return ws.antecedentesPenales(persona);
    }


    public String consultaNombre (long id) {
        Persona persona = em.find(Persona.class, id);
        return persona.getNombre();
    }
    */

    public Persona consultaPersona (long id) {
        return em.find(Persona.class, id);
    }
}
