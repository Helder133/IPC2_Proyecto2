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

/**
 *
 * @author helder
 */
public class UsuarioService {

    public Usuario createUsuario(NewUserRequest newUserRequest) throws UserDataInvalidException,
            EntityAlreadyExistsException {
        UserDB userDb = new UserDB();
        
        return null;
    }

}
