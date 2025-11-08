/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.adminCineService;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.AdminCineDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.adminCIne.AdminCineRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine.AdminCine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.cine.Cine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.UsuarioTypeEnum;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.cine.CineService;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.users.UsuarioService;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class AdminCineService {
    
    public void assignAdministrator (AdminCineRequest adminCineRequest) throws SQLException, EntityAlreadyExistsException, UserDataInvalidException {
        Cine cine = extraerCine(adminCineRequest.getTelefono());
        Usuario usuario = extraerUsuario(adminCineRequest.getEmail());
        if (!usuario.getUsuarioTypeEnum().equals(UsuarioTypeEnum.Administrador_Cine)) {
            throw new UserDataInvalidException(String.format("El usuario %s no es un administrador cine", usuario.getNombre()));
        }
        AdminCine adminCine = new AdminCine(usuario.getUsuario_Id(), cine.getCine_Id());
        AdminCineDB adminCineDB = new AdminCineDB();
        adminCineDB.insert(adminCine);
        
    }
    
    private Cine extraerCine(String telefono) throws SQLException, EntityAlreadyExistsException {
        CineService cineService = new CineService();
        Optional<Cine> cineOpt = cineService.existsTelefono(telefono);
        if (cineOpt.isEmpty()) {
            throw new EntityAlreadyExistsException("El cine con el que quiere relacionar al Administrador Cine no exite");
        }
        return cineOpt.get();
    }
    
    private Usuario extraerUsuario(String email) throws SQLException, EntityAlreadyExistsException {
        UsuarioService usuarioService = new UsuarioService();
        Optional<Usuario> usuarioOpt = usuarioService.existsEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new EntityAlreadyExistsException("El Administrador Cine que quiere relacionar al cine no existe");
        }
        return usuarioOpt.get();
    }
    
}
