/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.users;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.UserDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.NewUserRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author helder
 */
public class UsuarioService {

    public Usuario createUsuario(NewUserRequest newUserRequest) throws UserDataInvalidException,
            EntityAlreadyExistsException,
            SQLException {
        UserDB userDb = new UserDB();
        
        Usuario usuario = extracUser(newUserRequest);
        
        if (userDb.existsEmail(newUserRequest.getEmail())) {
            throw new EntityAlreadyExistsException(
                String.format("El Usuario con el Email %s ya existe", newUserRequest.getEmail()));
        }
        
        userDb.insert(usuario);
        
        return usuario;
    }
    
    private Usuario extracUser(NewUserRequest newUserRequest) throws UserDataInvalidException {
        try {
            Usuario usuario = new Usuario (
                    newUserRequest.getNombre(),
                    newUserRequest.getEmail(),
                    newUserRequest.getContraseña(),
                    newUserRequest.getRol()
            );
            if (usuario.isValid()) {
                throw new UserDataInvalidException("Error en los datos enviados");
            }
            return usuario;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
    }
    
    public List<Usuario> getAllUsers() throws SQLException {
        UserDB userDB = new UserDB();
        
        return userDB.select();
    }
    
}
