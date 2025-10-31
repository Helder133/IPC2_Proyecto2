/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Login;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;
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
public class UserDB implements CRUD<Usuario> {

    private static final String LOGIN = "SELECT * FROM Usuario WHERE Email = ? AND Contraseña = ?";
    private static final String CREAR_USUARIO_QURY = "INSERT INTO Usuario (Nombre, Email, Contraseña, Rol) VALUE (?,?,?,?);";
    public Optional<Usuario> loginUser(Login login) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement userlogin = connection.prepareStatement(LOGIN)) {
            userlogin.setString(1, login.getUser());
            userlogin.setString(2, login.getPassword());
            ResultSet resultSet = userlogin.executeQuery();
            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Email"),
                        resultSet.getString("Contraseña"),
                        resultSet.getString("Rol")
                );
                usuario.setUsuario_Id(resultSet.getInt("Usuario_Id"));
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Usuario insert(Usuario t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QURY)) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getEmail());
            insert.setString(3, t.getContraseña());
            insert.setString(4, t.getRol());
            insert.executeUpdate();
        }
        return t;
    }

    @Override
    public void update(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario selectById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> selectByString(String code) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean existsEmail(String email) throws SQLException {
    String query = "SELECT * FROM Usuario WHERE Email = ?";
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(query)) {
            select.setString(1, email);
            ResultSet result = select.executeQuery();
            return result.next();
        }
    }
    
}
