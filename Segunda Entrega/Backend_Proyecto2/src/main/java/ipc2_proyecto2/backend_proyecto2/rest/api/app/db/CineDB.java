/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.cine.Cine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.type.EstadoTypeEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class CineDB implements CRUD<Cine> {

    private static final String INSERTAR_NUEVO_CINE = "INSERT INTO Cine (Nombre, Telefono, Direccion, Estado, Fecha_Creacion) VALUE (?,?,?,?,?)";
    private static final String SELECCIONAR_TODOS_LOS_CINES = "SELECT * FROM Cine";
    private static final String SELECCIONAR_POR_COINCIDENCIA = "SELECT * FROM Cine WHERE Nombre LIKE ?";
    private static final String SELECCIONAR_POR_INT = "SELECT * FROM Cine WHERE Cine_Id = ?";
    private static final String ACTUALIZAR_CINE = "UPDATE Cine SET Nombre = ?, Telefono = ?, Direccion = ?, Estado = ?, Fecha_Creacion = ? WHERE Cine_Id = ?";
    private static final String ELIMINAR_CINE = "DELETE FROM Cine WHERE Cine_Id = ?";
    private static final String EXISTS_TELEFONO = "SELECT * FROM Cine WHERE Telefono = ?";
    private static final String VERIFICAR_NUEVO_NUMERO = "SELECT * FROM Cine WHERE Telefono = ? AND Cine_Id <> ?";
    
    @Override
    public Cine insert(Cine t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSERTAR_NUEVO_CINE); 
                PreparedStatement query = connection.prepareStatement(EXISTS_TELEFONO)) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getTelefono());
            insert.setString(3, t.getDireccion());
            insert.setString(4, t.getEstadoTypeEnum().toString());
            insert.setDate(5, Date.valueOf(t.getFechaCreacion()));
            insert.executeUpdate();
            
            //Obteniendo su id
            query.setString(1, t.getTelefono());
            ResultSet result = query.executeQuery();
            if (result.next()) {
                t.setCine_Id(result.getInt("Cine_Id"));
            }
            return t;
        }
    }

    @Override
    public void update(Cine t) throws SQLException, EntityAlreadyExistsException {
        if (!existsCine(t.getCine_Id())) {
            throw new EntityAlreadyExistsException("El cine que desea actualizar no exite");
        }
        if (verificarNuevoNumero(t)) {
            throw new EntityAlreadyExistsException("El numero de telefono ya esta relacionado con otro cine");
        }
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement update = connection.prepareStatement(ACTUALIZAR_CINE)) {
            update.setString(1, t.getNombre());
            update.setString(2, t.getTelefono());
            update.setString(3, t.getDireccion());
            update.setString(4, t.getEstadoTypeEnum().toString());
            update.setDate(5, Date.valueOf(t.getFechaCreacion()));
            update.setInt(6, t.getCine_Id());
        }
    }

    @Override
    public void delete(Cine t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement delete = connection.prepareStatement(ELIMINAR_CINE)) {
            delete.setInt(1, t.getCine_Id());
            delete.executeUpdate();
        }
    }

    @Override
    public List<Cine> select() throws SQLException {
        List<Cine> cines = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECCIONAR_TODOS_LOS_CINES)) {
            ResultSet resultSet = select.executeQuery();
            while (resultSet.next() && contador <= max) {
                contador++;
                Cine cine = new Cine(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Direccion"),
                        EstadoTypeEnum.valueOf(resultSet.getString("Estado")),
                        resultSet.getDate("Fecha_Creacion").toLocalDate()
                );
                cine.setCine_Id(resultSet.getInt("Cine_Id"));
                
                cines.add(cine);
            }
            return cines;
        }
    }

    @Override
    public Optional<Cine> selectById(int id) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECCIONAR_POR_INT)) {
            select.setInt(1, id);
            
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                Cine cine = new Cine (resultSet.getString("Nombre"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Direccion"),
                        EstadoTypeEnum.valueOf(resultSet.getString("Estado")),
                        resultSet.getDate("Fecha_Creacion").toLocalDate()
                );
                cine.setCine_Id(resultSet.getInt("Cine_Id"));
                
                return Optional.of(cine);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Cine> selectByString(String code) throws SQLException {
        List<Cine> cines = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECCIONAR_POR_COINCIDENCIA)) {
            select.setString(1, "%"+code+"%");
            
            ResultSet resultSet = select.executeQuery();
            
            while (resultSet.next() && contador <= max) {
                contador++;
                Cine cine = new Cine(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Direccion"),
                        EstadoTypeEnum.valueOf(resultSet.getString("Estado")),
                        resultSet.getDate("Fecha_Creacion").toLocalDate()
                );
                cine.setCine_Id(resultSet.getInt("Cine_Id"));
                
                cines.add(cine);
            }
            return cines;
        }
    }

    public Optional<Cine> existsTelefono(String telefono) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(EXISTS_TELEFONO)) {
            insert.setString(1, telefono);
            ResultSet resultSet = insert.executeQuery();
            if (resultSet.next()) {
                Cine cine = new Cine (resultSet.getString("Nombre"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Direccion"),
                        EstadoTypeEnum.valueOf(resultSet.getString("Estado")),
                        resultSet.getDate("Fecha_Creacion").toLocalDate()
                );
                cine.setCine_Id(resultSet.getInt("Cine_Id"));
                
                return Optional.of(cine);
            }
        }
        return Optional.empty();
    }
    
    private boolean existsCine(int cine_Id) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(SELECCIONAR_POR_INT)) {
            insert.setInt(1, cine_Id);
            ResultSet result = insert.executeQuery();
            return result.next();
        }
    }
    private boolean verificarNuevoNumero (Cine cine) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(VERIFICAR_NUEVO_NUMERO)) {
            insert.setString(1, cine.getTelefono());
            insert.setInt(2, cine.getCine_Id());
            ResultSet result = insert.executeQuery();
            return result.next();
        }
    }

}
