/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.users;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.UserDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.NewUserRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.UpdateUserRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

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
                    String.format("El Email %s ya esta relacionado con otro usuario", newUserRequest.getEmail()));
        }

        userDb.insert(usuario);

        return usuario;
    }

    private Usuario extracUser(NewUserRequest newUserRequest) throws UserDataInvalidException {
        try {
            Usuario usuario = new Usuario(
                    newUserRequest.getNombre(),
                    newUserRequest.getEmail(),
                    newUserRequest.getContraseña(),
                    newUserRequest.getUsuarioTypeEnum()
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

    public Usuario getUsuarioByInt(int code) throws SQLException, UserDataInvalidException {
        UserDB userDB = new UserDB();
        Optional<Usuario> usuarioOpt = userDB.selectById(code);
        if (usuarioOpt.isEmpty()) {
            throw new UserDataInvalidException("Error al obtener al usuario");
        }
        return usuarioOpt.get();
    }

    public List<Usuario> getUserByString(String code) throws SQLException, UserDataInvalidException {
        UserDB userDB = new UserDB();

        return userDB.selectByString(code);
    }

    public Usuario updateUsuario(int code, UpdateUserRequest usuarioRequest) throws UserDataInvalidException,
            SQLException,
            EntityAlreadyExistsException {
        UserDB userDB = new UserDB();

        Usuario usuario = getUsuarioByInt(code);

        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEmail(usuarioRequest.getEmail());

        boolean bandera = false;
        if (StringUtils.isBlank(usuarioRequest.getContraseña())) {
            usuario.setContraseña("123");
            bandera = true;
        } else {
            Usuario usuarioUpdate = new Usuario(
                    usuarioRequest.getNombre(),
                    usuarioRequest.getEmail(),
                    usuarioRequest.getContraseña(),
                    usuarioRequest.getUsuarioTypeEnum());

            usuario.setContraseña(usuarioUpdate.getContraseña());
        }

        usuario.setUsuarioTypeEnum(usuarioRequest.getUsuarioTypeEnum());

        if (usuario.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }

        if (bandera) {
            usuario.setContraseña(null);
        }

        userDB.update(usuario);

        return usuario;
    }

    public void deleteUserByCode(int code) throws SQLException,
            UserDataInvalidException, EntityAlreadyExistsException {
        UserDB userDB = new UserDB();
        Optional<Usuario> usuarioOpt = userDB.selectById(code);
        if (usuarioOpt.isEmpty()) {
            throw new EntityAlreadyExistsException(
                    "El usuario que trata de eliminar, no existe");
        }
        userDB.delete(usuarioOpt.get());
    }

}
