package com.chema.h2.h2api.controlador;

import com.chema.h2.h2api.modelo.Usuario;
import com.chema.h2.h2api.servicio.ServicioUsuario;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class ControladorUsuario {

    private final ServicioUsuario servicio;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(servicio.guardarUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id) {
        return servicio.obtenerUsuario(id).map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(servicio.modificarUsuario(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
        return Optional.of(servicio.eliminarUsuario(id))
                .map(deleted -> new ResponseEntity<Boolean>(HttpStatus.OK))
                .orElseGet( () -> new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND));
    }
}
