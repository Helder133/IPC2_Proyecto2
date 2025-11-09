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
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine.AdminCineJoin;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.cine.Cine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.UsuarioTypeEnum;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.cine.CineService;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.users.UsuarioService;
import java.sql.SQLException;
import java.util.List;
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
        if (!verificarSiEsAdmin(usuario.getUsuario_Id())) {
            throw new UserDataInvalidException(String.format("El usuario %s, con email %s ya esa administrador de un cine", usuario.getNombre(), usuario.getEmail()));
        }
        if (!verificarSiCineYaTieneAdmin(cine.getCine_Id())) {
            throw new UserDataInvalidException(String.format("El cine %s, con numero %s ya cuenta con un administrador", cine.getNombre(), cine.getTelefono()));
        }
        AdminCine adminCine = new AdminCine(usuario.getUsuario_Id(), cine.getCine_Id());
        if (adminCine.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
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
    
    private boolean verificarSiEsAdmin(int usuario) throws SQLException {
        AdminCineDB adminCineDB = new AdminCineDB();
        Optional<AdminCineJoin> adminCineJoinOpt = adminCineDB.verificarAdminUsuario(usuario);
        return adminCineJoinOpt.isEmpty();
    }
    
    private boolean verificarSiCineYaTieneAdmin(int cine) throws SQLException {
        AdminCineDB adminCineDB = new AdminCineDB();
        Optional<AdminCineJoin> adminCineJoinOpt = adminCineDB.verificarAdminCine(cine);
        return adminCineJoinOpt.isEmpty();
    }
    
    public List<AdminCineJoin> getAllAdminCine () throws SQLException {
        AdminCineDB adminCineDB = new AdminCineDB();
        return adminCineDB.getAllAdminCine();
    }
    
    public void deleteAdminCine (int usuario) throws SQLException, EntityAlreadyExistsException {
        AdminCineDB adminCineDB = new AdminCineDB();
        if (verificarSiEsAdmin(usuario)) {
            throw new EntityAlreadyExistsException("El administrador que intenta eliminar no existe ");
        }
        adminCineDB.delete(usuario);
    }
}
