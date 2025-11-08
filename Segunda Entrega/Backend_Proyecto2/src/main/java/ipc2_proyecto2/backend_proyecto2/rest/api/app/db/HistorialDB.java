/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.historial.Historial;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class HistorialDB implements CRUD<Historial>{
    private static final String CREAR_HISTORIAL_DE_CARTERA = "INSERT INTO Historial (Cartera_Digital_Id, Fecha, Transaccion, Descripcion) VALUE (?,?,?,?)";
    
    @Override
    public Historial insert(Historial t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_HISTORIAL_DE_CARTERA)){
            insert.setInt(1, t.getCartera_Digital_Id());           
            insert.setDate(2, Date.valueOf(t.getFecha()));           
            insert.setString(3, t.getHistorialTypeEnum().toString());           
            insert.setString(4, t.getDescripcion());
            insert.executeUpdate();
        }
        return t;
    }

    @Override
    public void update(Historial t) throws SQLException, EntityAlreadyExistsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Historial t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Historial> select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Historial> selectById(int id) throws SQLException, UserDataInvalidException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Historial> selectByString(String code) throws SQLException, UserDataInvalidException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
