package edu.udea.main.business;

import edu.udea.main.model.Usuario;

import java.util.ArrayList;

public class GestorUsuario {

    private ArrayList<Usuario> usuarios;


//Instancia de usuarios
    public GestorUsuario (){
        this.usuarios = new ArrayList<>();

        this.usuarios.add(new Usuario("Prueba1","Prueba", "1234"));
        this.usuarios.add(new Usuario("Prueba1","Andres", "1234"));

    }

   //Verificacion de existencia de usuario
    public Usuario getUsuario (String nombreUsuario) throws Exception{
        for (Usuario usuario: this.usuarios){
            if (usuario.getNombreUsuario().equals(nombreUsuario) ){
                return usuario;
            }
        }
        throw new Exception("Usuario No Existe.");
    }


    //Creacion y verificación de existencia de usuarios
    public String setUsuario (Usuario usuario) throws Exception{
        try {
            getUsuario (usuario.getNombreUsuario());

        }catch (Exception e){

            this.usuarios.add(usuario);
            return "Creación de Usuario Exitosa.";
        }
        throw new Exception("Usuario Existe.");
    }


        //Metodo para usar el PATCH y actualizar solo una o mas variables
    public Usuario updateUsuario(Usuario usuario_update, String id) throws Exception {
        try {
            Usuario usuario_bd = getUsuario(id);
            if (usuario_update.getNombreUsuario() != null) {
                usuario_bd.setNombre(usuario_update.getNombreUsuario());
            }
            if (usuario_update.getNombre() != null) {
                usuario_bd.setNombre(usuario_update.getNombre());
            }
            if (usuario_update.getPassword() != null) {
                usuario_bd.setPassword(usuario_update.getPassword());
            }
            return usuario_bd;
        } catch (Exception e) {
            throw new Exception("Usuario NO existe, falló actualización de datos.");
        }

    }


        /* Metodo para usar el PUT, Y actualizar el objeto completo*/
    public Usuario updateUsuarioAll(Usuario usuario_update, String id) throws Exception {

        try {
            Usuario usuario_bd = getUsuario(id);
            usuario_bd.setNombreUsuario(usuario_update.getNombreUsuario());
            usuario_bd.setNombre(usuario_update.getNombre());
            usuario_bd.setPassword(usuario_update.getPassword());

            return usuario_bd;
        } catch (Exception e) {
            throw new Exception("Usuario NO existe, falló actualización de datos.");
        }
    }



        //DELETE metodo para usar el DELETE en el controlador

        public String deleteUsuario (String id) throws Exception {
            try {
                Usuario usuario = getUsuario(id);
                this.usuarios.remove(usuario);
                return "Usuario Eliminado Existosamente.";
            }catch (Exception e) {
                throw new Exception("Usuario NO Existe para Eliminar.");
            }
        }








    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
