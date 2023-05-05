package com.chema.h2.h2api.servicio;

import com.chema.h2.h2api.modelo.Usuario;

import java.util.Optional;

public interface ServicioUsuario {
    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuario(Long idUsuario);

    Usuario modificarUsuario(Long id, Usuario usuario);

    boolean eliminarUsuario(Long id);
}
