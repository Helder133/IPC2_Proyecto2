/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.login;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.login.UserLogin;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Login;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.UserDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class LoginService {
    
    public Usuario loginUser (UserLogin userLogin) throws SQLException, UserDataInvalidException{
        UserDB userDB = new UserDB();
        Login login = new Login(userLogin.getUser(), 
                userLogin.getPassword());
        
        if (login.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
        
        Optional<Usuario> usuarioOpt = userDB.loginUser(login);
        if (usuarioOpt.isEmpty()){
            throw new UserDataInvalidException("Usuario o Contraseña incorrecta, vuelva a intentar");
        }
        
        return usuarioOpt.get();
    }
    
}
