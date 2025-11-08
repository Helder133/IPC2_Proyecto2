/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.carteraDigital.Cartera_Digital;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.login.Login;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.UsuarioTypeEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class UserDB implements CRUD<Usuario> {

    private static final String LOGIN = "SELECT * FROM Usuario WHERE Email = ? AND Contraseña = ?";
    private static final String CREAR_USUARIO_QURY = "INSERT INTO Usuario (Nombre, Email, Contraseña, Rol) VALUE (?,?,?,?);";
    private static final String BUSCAR_POR_EMAIL = "SELECT * FROM Usuario WHERE Email = ?";
    private static final String TODOS_LOS_USUARIOS = "SELECT * FROM Usuario";
    private static final String USUARIO_POR_INT = "SELECT * FROM Usuario WHERE Usuario_Id = ?";
    private static final String USUARIOS_BUSCADOS_POR_NOMBRE = "SELECT * FROM Usuario WHERE Nombre LIKE ?";
    private static final String VERIFICAR_NUEVO_EMAIL = "SELECT * FROM Usuario WHERE Email = ? AND Usuario_Id <> ?";
    private static final String ACTUALIZAR_USUARIO_SIN_PASSWORD = "UPDATE Usuario SET Nombre = ?, Email = ?, Rol = ? WHERE Usuario_Id = ?";
    private static final String ACTUALIZAR_USUARIO_CON_PASSWORD = "UPDATE Usuario SET Nombre = ?, Email = ?, Contraseña = ?,Rol = ? WHERE Usuario_Id = ?";
    private static final String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE Usuario_Id = ?";
    
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
                        UsuarioTypeEnum.valueOf(resultSet.getString("Rol"))
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
        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QURY); PreparedStatement select = connection.prepareStatement(BUSCAR_POR_EMAIL)) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getEmail());
            insert.setString(3, t.getContraseña());
            insert.setString(4, t.getUsuarioTypeEnum().toString());
            insert.executeUpdate();

            //Obteniendo su Id
            select.setString(1, t.getEmail());
            ResultSet result = select.executeQuery();
            if (result.next()) {
                t.setUsuario_Id(result.getInt("Usuario_Id"));
            }
        }

        return t;
    }

    @Override
    public void update(Usuario t) throws SQLException, EntityAlreadyExistsException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        
        if (verificarEmail(t)) {
            throw new EntityAlreadyExistsException(String.format("El email %s ya esta relacionado con otro usuario", t.getEmail()));
        }
        
        if (StringUtils.isBlank(t.getContraseña())) {
            try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_SIN_PASSWORD)){
                insert.setString(1, t.getNombre());
                insert.setString(2, t.getEmail());
                insert.setString(3, t.getUsuarioTypeEnum().toString());
                insert.setInt(4, t.getUsuario_Id());
                insert.executeUpdate();
            }
        } else {
            try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_CON_PASSWORD)){
                insert.setString(1, t.getNombre());
                insert.setString(2, t.getEmail());
                insert.setString(3, t.getContraseña());
                insert.setString(4, t.getUsuarioTypeEnum().toString());
                insert.setInt(5, t.getUsuario_Id());
                insert.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Usuario t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try(PreparedStatement deleteStatement = connection.prepareStatement(ELIMINAR_USUARIO)) {
            
            deleteStatement.setInt(1, t.getUsuario_Id());
            deleteStatement.executeUpdate();
        }
    }

    @Override
    public List<Usuario> select() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(TODOS_LOS_USUARIOS);) {
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Email"),
                        resultSet.getString("Contraseña"),
                        UsuarioTypeEnum.valueOf(resultSet.getString("Rol"))
                );
                usuario.setUsuario_Id(resultSet.getInt("Usuario_Id"));
                usuario.setContraseña("");
                usuarios.add(usuario);
            }
        }
        return usuarios;

    }

    @Override
    public Optional<Usuario> selectById(int id) throws SQLException, UserDataInvalidException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(USUARIO_POR_INT)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Email"),
                        resultSet.getString("Contraseña"),
                        UsuarioTypeEnum.valueOf(resultSet.getString("Rol"))
                );
                usuario.setUsuario_Id(resultSet.getInt("Usuario_Id"));
                usuario.setContraseña("");

                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> selectByString(String code) throws SQLException, UserDataInvalidException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(USUARIOS_BUSCADOS_POR_NOMBRE)) {
            query.setString(1, "%" + code + "%");

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Email"),
                        resultSet.getString("Contraseña"),
                        UsuarioTypeEnum.valueOf(resultSet.getString("Rol"))
                );

                usuario.setUsuario_Id(resultSet.getInt("Usuario_Id"));
                usuario.setContraseña("");

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public Optional<Usuario> existsEmail(String email) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(BUSCAR_POR_EMAIL)) {
            select.setString(1, email);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Email"),
                        resultSet.getString("Contraseña"),
                        UsuarioTypeEnum.valueOf(resultSet.getString("Rol"))
                );
                usuario.setUsuario_Id(resultSet.getInt("Usuario_Id"));
                usuario.setContraseña("");

                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
    
    private boolean verificarEmail(Usuario usuario) throws SQLException{
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(VERIFICAR_NUEVO_EMAIL)) {
            select.setString(1, usuario.getEmail());
            select.setInt(2, usuario.getUsuario_Id());
            ResultSet result = select.executeQuery();
            return result.next();
        }
    }
    
}
