/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.clasificacion;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.ClasificacionDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion.ClasificacionRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion.ClasificacionUpdate;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.clasificacion.Clasificacion;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class ClasificacionService {

    public void createClasificacion(ClasificacionRequest clasificacionRequest) throws UserDataInvalidException, SQLException, EntityAlreadyExistsException {
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        Clasificacion clasificacion = extraerDatos(clasificacionRequest);
        if (clasificacionDB.existsNombre(clasificacion.getNombre())) {
            throw new EntityAlreadyExistsException(String.format("El nombre %s ya esta asociado con otra clasificacion", clasificacion.getNombre()));
        }
        clasificacionDB.insert(clasificacion);
    }

    private Clasificacion extraerDatos(ClasificacionRequest clasificacionRequest) throws UserDataInvalidException {
        Clasificacion clasificacion = new Clasificacion(clasificacionRequest.getNombre(), clasificacionRequest.getEdadMinima(),
                clasificacionRequest.getDescripcion());
        if (clasificacion.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
        return clasificacion;
    }
    
    public List<Clasificacion> getAllClasificacion () throws SQLException {
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        return clasificacionDB.select();
    }
    
    public List<Clasificacion> gatBYString (String nombre) throws SQLException {
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        return clasificacionDB.selectByString(nombre);
    }
    
    public Clasificacion getByInt (int code) throws SQLException, EntityAlreadyExistsException {
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        Optional<Clasificacion> clasificacionOpt = clasificacionDB.selectById(code);
        if (clasificacionOpt.isEmpty()) {
            throw new EntityAlreadyExistsException("No se puedo encontrar la clasificacion solicitada");
        }
        return clasificacionOpt.get();
    }
    
    public void updateClasificacion (int code, ClasificacionUpdate clasificacionUpdate) throws UserDataInvalidException, SQLException, EntityAlreadyExistsException {
        Clasificacion clasificacion = extraerDatos(clasificacionUpdate);
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        if (clasificacionDB.verificarNewName(clasificacion)) {
            throw new EntityAlreadyExistsException(String.format("El nombre %s ya esta usado", clasificacion.getNombre()));
        }
        clasificacion.setCasificacion_Id(code);
        clasificacionDB.update(clasificacion);
    }
    
    private Clasificacion extraerDatos(ClasificacionUpdate clasificacionUpdate) throws UserDataInvalidException {
        Clasificacion clasificacion = new Clasificacion(clasificacionUpdate.getNombre(), clasificacionUpdate.getEdadMinima(),
                clasificacionUpdate.getDescripcion());
        if (clasificacion.isValid()) {
            throw new UserDataInvalidException("Error en los datos enviados");
        }
        return clasificacion;
    }
    
    public void deleteClasificacion (int code) throws SQLException, UserDataInvalidException {
        ClasificacionDB clasificacionDB = new ClasificacionDB();
        Optional<Clasificacion> clasificaionOpt = clasificacionDB.selectById(code);
        if (clasificaionOpt.isEmpty()) {
            throw new UserDataInvalidException("No se puedo encontrar el objeto a eliminar");
        }
        clasificacionDB.delete(clasificaionOpt.get());
    }
}
