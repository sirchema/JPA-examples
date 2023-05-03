package chema.jpa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonaController {

    @Autowired
    private Servicio servicio;

    @Autowired
    private ServicioAuditoria servicioAuditoria;

    @RequestMapping(path = "/alta")
    @ResponseBody
    public Persona alta (String nombre) {
        return servicio.alta(nombre);
    }

    @RequestMapping(path = "/consulta")
    @ResponseBody
    public Persona consulta (long id) {
        return servicio.consulta(id);
    }

    @RequestMapping(path = "/logs")
    @ResponseBody
    public List<Auditoria> logs() {
        return servicioAuditoria.todas();
    }
}
