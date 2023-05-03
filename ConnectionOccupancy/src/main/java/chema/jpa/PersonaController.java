package chema.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonaController {

    @Autowired
    private Servicio servicio;

    @Autowired
    private WebService ws;

    @RequestMapping(path = "/antecedentes")
    @ResponseBody
    public String antecedentes (long id) {
        Persona persona = servicio.consultaPersona(id);

        return ws.antecedentesPenales(persona);
    }

    @RequestMapping(path = "/nombre")
    @ResponseBody
    public String nombre (long id) {
        //return servicio.consultaNombre(id);
        return servicio.consultaPersona(id).getNombre();
    }
}
