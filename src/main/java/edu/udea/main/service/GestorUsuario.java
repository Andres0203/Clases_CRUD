package edu.udea.main.service;

import edu.udea.main.model.Usuario;
import edu.udea.main.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GestorUsuario implements GestorUsuarioInterface{

    @Autowired
    private UsuarioRepositorio repositorio;


    @Override
    public List<Usuario> getUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario getUsuario(String id) throws Exception {
        Optional <Usuario> usuariobd = repositorio.findById(id);
        if(usuariobd.isPresent()){
            return usuariobd.get();
        }
        throw new Exception("Usuario No Existe.") ;
    }

    @Override
    public String setUsuario(Usuario usuario_parametro) {
        repositorio.save(usuario_parametro);
        return "Usuario creado con Exito.";
    }

    @Transactional
    @Override
    public Usuario updateUsuarioAll(Usuario usuario_update, String id) throws Exception {
        repositorio.update(usuario_update.getNombre(),usuario_update.getPassword(), id);
        return getUsuario(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario_update, String id) throws Exception {
        Usuario usuario_bd =getUsuario(id);

        if (usuario_update.getNombre() != null && usuario_update.getNombre().equals("")){
            usuario_bd.setNombre(usuario_update.getNombre());
        }
        if (usuario_update.getPassword() != null && usuario_update.getPassword().equals("")){
            usuario_bd.setPassword(usuario_update.getPassword());

        }
        return repositorio.save(usuario_bd);
    }

    @Override
    public String deleteUsuario(String id) {
        repositorio.deleteById(id);
        return "Usuario Eliminado Exitosamente.";
    }
}
