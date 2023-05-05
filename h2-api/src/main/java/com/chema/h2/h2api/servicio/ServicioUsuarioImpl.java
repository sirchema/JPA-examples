package com.chema.h2.h2api.servicio;

import com.chema.h2.h2api.modelo.Usuario;
import com.chema.h2.h2api.repositorio.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioUsuarioImpl implements ServicioUsuario{

    private final RepositorioUsuario repositorio;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Long idUsuario) {
        return repositorio.findById(idUsuario);
    }

    @Override
    public Usuario modificarUsuario(Long id, Usuario usuario) {
        Usuario usuarioOld = repositorio.findById(id).get();
        usuarioOld.setDireccion(usuario.getDireccion());
        return this.repositorio.save(usuarioOld);
    }

    @Override
    public boolean eliminarUsuario(Long id) {
        try {
            this.repositorio.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
