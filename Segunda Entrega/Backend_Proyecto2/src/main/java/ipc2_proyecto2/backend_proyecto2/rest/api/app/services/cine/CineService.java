/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.cine;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.CineDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine.CineRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine.CineUpdate;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.cine.Cine;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class CineService {

    public Cine createCine(CineRequest cineRequest) throws UserDataInvalidException, SQLException, EntityAlreadyExistsException {
        CineDB cineDB = new CineDB();
        Cine cine = extraerCine(cineRequest);
        
        if (!existsTelefono(cineRequest.getTelefono()).isEmpty()) {
            throw new EntityAlreadyExistsException(
                    String.format("El Telefono %s ya esta relacionado con otro cine", cineRequest.getTelefono()));
        }

        return cineDB.insert(cine);
    }

    private Cine extraerCine(CineRequest cineRequest) throws UserDataInvalidException {
        try {
            Cine cine = new Cine(
                    cineRequest.getNombre(),
                    cineRequest.getTelefono(),
                    cineRequest.getDireccion(),
                    cineRequest.getEstadoTypeEnum(),
                    cineRequest.getFechaCreacion()
            );
            if (cine.isValid()) {
                throw new UserDataInvalidException("Error en los datos enviados");
            }
            return cine;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
    }

    public Optional<Cine> existsTelefono(String telefono) throws SQLException {
        CineDB cineDB = new CineDB();
        return cineDB.existsTelefono(telefono);
    }

    public List<Cine> getAllCine() throws SQLException {
        CineDB cineDB = new CineDB();
        return cineDB.select();
    }

    public Cine getCineByInt(int code1) throws SQLException, UserDataInvalidException {
        CineDB cineDB = new CineDB();
        Optional<Cine> cineOpt = cineDB.selectById(code1);
        if (cineOpt.isEmpty()) {
            throw new UserDataInvalidException("Error al obtener al cine");
        }
        return cineOpt.get();
    }

    public List<Cine> getCineByString(String code) throws SQLException {
        CineDB cineDB = new CineDB();
        return cineDB.selectByString(code);
    }

    public Cine updateCine(int code, CineUpdate cineUpdate) throws UserDataInvalidException, SQLException, EntityAlreadyExistsException {
        CineDB cineDB = new CineDB();
        Cine cine = new Cine(
                cineUpdate.getNombre(),
                cineUpdate.getTelefono(),
                cineUpdate.getDireccion(),
                cineUpdate.getEstadoTypeEnum(),
                cineUpdate.getFechaCreacion()
        );
        if (cine.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
        cine.setCine_Id(code);
        cineDB.update(cine);
        return cine;
    }

    public void deleteCineByCode(int code) throws SQLException, EntityAlreadyExistsException {
        CineDB cineDB = new CineDB();
        Optional<Cine> cineOpt = cineDB.selectById(code);
        if (cineOpt.isEmpty()) {
            throw new EntityAlreadyExistsException("El cine que trata de eliminar, no existe");
        }
        cineDB.delete(cineOpt.get());
    }

}
