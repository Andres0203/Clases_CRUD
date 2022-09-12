package edu.udea.main.controller;


import edu.udea.main.business.GestorUsuario;
import edu.udea.main.business.GestorUsuarioInterface;
import edu.udea.main.business.GestorUsuarioList;
import edu.udea.main.model.ObjetoRespuesta;
import edu.udea.main.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioControlador {

    @Autowired
    private GestorUsuarioInterface gestorUsuario;

    // Metodo GET con QUERY PARAMS
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuario() {
        return new ResponseEntity<>(gestorUsuario.getUsuarios(), HttpStatus.OK);
    }


    // Metodo GET Con la verificación de existencia de usuario
    @GetMapping ("/usuario")
    public ResponseEntity<Object> getUsuario (@RequestParam String id){

        try {
            Usuario usuario = gestorUsuario.getUsuario(id);
            return new ResponseEntity<>(usuario,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //Metodo GET con PATH PARAMS
    @GetMapping("/usuario/{id}/edit")
    public ResponseEntity<String> getUsuarioPath(@PathVariable String id){
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



    //Metodo POST con verificación de usuario
    @PostMapping("/usuario")
    public ResponseEntity<String> postUsuario(@RequestBody Usuario usuario){

        try {
            String mensaje = gestorUsuario.setUsuario(usuario);

            return new ResponseEntity<>(mensaje, HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

        //PUT sirve para actualizar el objeto completo
    @PutMapping("/usuario/{id}")
    public ResponseEntity<ObjetoRespuesta> putUsuario(@RequestBody Usuario usuario_update, @PathVariable String id){
        try {
            Usuario usuario_bd = gestorUsuario.updateUsuarioAll(usuario_update, id);
            return new ResponseEntity<>(new ObjetoRespuesta("Actualizacion Exitosa",usuario_bd), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //PATCH sirve para cuando solo se necesita actualizar una parte del objeto

    @PatchMapping("/usuario/{id}")
    public ResponseEntity<ObjetoRespuesta> patchUsuario(@RequestBody Usuario usuario_update, @PathVariable String id){
        try {
            Usuario usuario_bd = gestorUsuario.updateUsuario(usuario_update, id);
            return new ResponseEntity<>(new ObjetoRespuesta("Actualizacion Exitosa", usuario_bd), HttpStatus.OK );

        } catch (Exception e){
            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }




    //DELETE Borrar cualquier usuario de la base de datos

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<ObjetoRespuesta> deleteUsuario(@PathVariable String id){
        try {
            String mensaje = gestorUsuario.deleteUsuario(id);

            return new ResponseEntity<>(new ObjetoRespuesta("Eliminado Exitosamente", null), HttpStatus.OK );

        } catch (Exception e) {

            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }



}
