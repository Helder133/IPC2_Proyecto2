/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.clasificacion.Clasificacion;
import java.sql.Connection;
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
public class ClasificacionDB implements CRUD<Clasificacion> {

    private static final String CREATE_CLASIFICACION = "INSERT INTO Clasificacion (Nombre, Edad_Minima, Descripcion) VALUE (?,?,?)";
    private static final String BUSCAR_NOMBRE_REPETIDO = "SELECT * FROM Clasificacion WHERE Nombre = ?";
    private static final String VERIFICAR_NUEVO_NOMBRE = "SELECT * FROM Clasificacion WHERE Nombre = ? AND Clasificacion_Id <> ?";
    private static final String UPDATE_CLASIFICACION = "UPDATE Clasificacion SET Nombre = ?, Edad_Minima = ?, Descripcion = ? WHERE Clasificacion_Id = ?";
    private static final String DELETE_CLASIFICACION = "DELETE FROM Clasificacion WHERE Clasificacion_Id = ?";
    private static final String SELECT_ALL_CLASIFICACION = "SELECT * FROM Clasificacion";
    private static final String SELECT_BY_INT = "SELECT * FROM Clasificacion WHERE Clasificacion_Id = ? ";
    private static final String SELECT_BY_STRING = "SELECT * FROM Clasificacion WHERE Nombre LIKE ? ";
    
    @Override
    public Clasificacion insert(Clasificacion t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREATE_CLASIFICACION)) {
            insert.setString(1, t.getNombre());
            insert.setInt(2, t.getEdadMinima());
            insert.setString(3, t.getDescripcion());
            insert.executeUpdate();
            return t;
        }
    }

    @Override
    public void update(Clasificacion t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(UPDATE_CLASIFICACION)) {
            insert.setString(1, t.getNombre());
            insert.setInt(2, t.getEdadMinima());
            insert.setString(3, t.getDescripcion());
            insert.setInt(4, t.getCasificacion_Id());
            insert.executeUpdate();
        }
    }

    @Override
    public void delete(Clasificacion t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement delete = connection.prepareStatement(DELETE_CLASIFICACION)) {
            delete.setInt(1, t.getCasificacion_Id());
            delete.executeUpdate();
        }
    }

    @Override
    public List<Clasificacion> select() throws SQLException {
        List<Clasificacion> clasificaciones = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECT_ALL_CLASIFICACION)) {
            ResultSet resultSet = select.executeQuery();
            while(resultSet.next() && contador <= max) {
                Clasificacion clasificacion = new Clasificacion(
                        resultSet.getString("Nombre"),
                        resultSet.getInt("Edad_Minima"),
                        resultSet.getString("Descripcion")
                );
                clasificacion.setCasificacion_Id(resultSet.getInt("Clasificacion_Id"));
                clasificaciones.add(clasificacion);
            }
        }
        return clasificaciones;
    }

    @Override
    public Optional<Clasificacion> selectById(int id) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECT_BY_INT)) {
            select.setInt(1, id);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                Clasificacion clasificacion = new Clasificacion(
                        resultSet.getString("Nombre"),
                        resultSet.getInt("Edad_Minima"),
                        resultSet.getString("Descripcion")
                );
                clasificacion.setCasificacion_Id(resultSet.getInt("Clasificacion_Id"));
                return Optional.of(clasificacion);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Clasificacion> selectByString(String code) throws SQLException {
        List<Clasificacion> clasificaciones = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(SELECT_BY_STRING)) {
            select.setString(1,"%"+code+"%");
            ResultSet resultSet = select.executeQuery();
            while(resultSet.next() && contador <= max) {
                Clasificacion clasificacion = new Clasificacion(
                        resultSet.getString("Nombre"),
                        resultSet.getInt("Edad_Minima"),
                        resultSet.getString("Descripcion")
                );
                clasificacion.setCasificacion_Id(resultSet.getInt("Clasificacion_Id"));
                clasificaciones.add(clasificacion);
            }
        }
        return clasificaciones;
    }

    public boolean existsNombre(String nombre) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement selectByName = connection.prepareStatement(BUSCAR_NOMBRE_REPETIDO)) {
            selectByName.setString(1, nombre);
            ResultSet resultSet = selectByName.executeQuery();
            return resultSet.next();
        }
    }

    public boolean verificarNewName(Clasificacion clasificacion) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement selectByName = connection.prepareStatement(VERIFICAR_NUEVO_NOMBRE)) {
            selectByName.setString(1, clasificacion.getNombre());
            selectByName.setInt(2, clasificacion.getCasificacion_Id());
            ResultSet resultSet = selectByName.executeQuery();
            return resultSet.next();
        }
    }
}
