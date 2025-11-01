/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Cartera_Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class Cartera_DigitalDB implements CRUD<Cartera_Digital>{
    private static final String OBTENER_SALDO = "SELECT * FROM Cartera_Digital WHERE Usuario_Id = ?";
    private static final String CREAR_CARTERA_DIGITAL = "INSERT INTO Cartera_Digital (Usuario_Id, Saldo) VALUE (?,?)";

    @Override
    public Cartera_Digital insert(Cartera_Digital t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_CARTERA_DIGITAL)) {
            insert.setInt(1, t.getUsuario_Id());
            insert.setFloat(2, t.getSaldo());
            insert.executeUpdate();   
        }
        return t;
    }

    @Override
    public void update(Cartera_Digital t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Cartera_Digital t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cartera_Digital> select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Cartera_Digital> selectById(int id) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(OBTENER_SALDO)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Cartera_Digital cartera_Digital = new Cartera_Digital(
                        resultSet.getInt("Usuario_Id"), 
                        resultSet.getFloat("Saldo"));
                
                cartera_Digital.setCartera_Digital_Id(resultSet.getInt("Cartera_Digital_Id"));
                return Optional.of(cartera_Digital);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Cartera_Digital> selectByString(String code) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
