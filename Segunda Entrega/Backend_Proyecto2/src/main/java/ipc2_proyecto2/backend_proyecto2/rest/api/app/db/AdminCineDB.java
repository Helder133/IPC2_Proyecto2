/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine.AdminCine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine.AdminCineJoin;
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
public class AdminCineDB{

    private static final String INSETAR_ADMINISTTRADORES = "INSERT INTO Admin_Cine (Usuario_Id, Cine_Id) VALUE (?,?)";
    private static final String VERIFICAR_ADMIN_POR_PARTE_DEL_USUARIO = "SELECT u.Usuario_Id, u.Nombre AS usuarioNombre, u.Email AS usuarioEmail, "
            + "c.Cine_Id, c.Nombre AS cineNombre, c.Telefono AS cineTelefono FROM Admin_Cine ac JOIN Usuario u ON ac.Usuario_Id "
            + "= u.Usuario_Id JOIN Cine c    ON ac.Cine_Id    = c.Cine_Id WHERE u.Usuario_Id = ?";
    private static final String VERIFICAR_ADMIN_POR_PARTE_DEL_CINE = "SELECT u.Usuario_Id, u.Nombre AS usuarioNombre, u.Email AS usuarioEmail, "
            + "c.Cine_Id, c.Nombre AS cineNombre, c.Telefono AS cineTelefono FROM Admin_Cine ac JOIN Usuario u ON ac.Usuario_Id "
            + "= u.Usuario_Id JOIN Cine c    ON ac.Cine_Id    = c.Cine_Id WHERE c.Cine_Id = ?";

    private static final String SELECT_ALL_ADMINCINES = "SELECT u.Usuario_Id, u.Nombre AS usuarioNombre, u.Email AS usuarioEmail, "
            + "c.Cine_Id, c.Nombre AS cineNombre, c.Telefono AS cineTelefono FROM Admin_Cine ac JOIN Usuario u ON ac.Usuario_Id "
            + "= u.Usuario_Id JOIN Cine c ON ac.Cine_Id = c.Cine_Id";
    private static final String ELIMINAR_ADMINCINE = "DELETE FROM Admin_Cine WHERE Usuario_Id = ?";

    public AdminCine insert(AdminCine t) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_ADMINISTTRADORES)) {
            insert.setInt(1, t.getUsuario_Id());
            insert.setInt(2, t.getCine_Id());
            insert.executeUpdate();
        }
        return t;
    }
    public void delete(int usuario) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement delete = connection.prepareStatement(ELIMINAR_ADMINCINE)) {
            delete.setInt(1, usuario);
            delete.executeUpdate();
        }
    }

    public Optional<AdminCineJoin> verificarAdminUsuario(int code) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(VERIFICAR_ADMIN_POR_PARTE_DEL_USUARIO)) {
            select.setInt(1, code);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                AdminCineJoin adminCineJoin = new AdminCineJoin(
                        resultSet.getInt("Usuario_Id"),
                        resultSet.getString("usuarioNombre"),
                        resultSet.getString("usuarioEmail"),
                        resultSet.getInt("Cine_Id"),
                        resultSet.getString("cineNombre"),
                        resultSet.getString("cineTelefono"));
                return Optional.of(adminCineJoin);
            }
        }
        return Optional.empty();
    }

    public Optional<AdminCineJoin> verificarAdminCine(int code) throws SQLException {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement select = connection.prepareStatement(VERIFICAR_ADMIN_POR_PARTE_DEL_CINE)) {
            select.setInt(1, code);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                AdminCineJoin adminCineJoin = new AdminCineJoin(
                        resultSet.getInt("Usuario_Id"),
                        resultSet.getString("usuarioNombre"),
                        resultSet.getString("usuarioEmail"),
                        resultSet.getInt("Cine_Id"),
                        resultSet.getString("cineNombre"),
                        resultSet.getString("cineTelefono"));
                return Optional.of(adminCineJoin);
            }
        }
        return Optional.empty();
    }

    public List<AdminCineJoin> getAllAdminCine() throws SQLException {
        List<AdminCineJoin> adminCineJoins = new ArrayList<>();
        int contador = 0;
        int max = 10;
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_ALL_ADMINCINES)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next() && contador <= max) {
                contador++;
                AdminCineJoin adminCineJoin = new AdminCineJoin(
                        resultSet.getInt("Usuario_Id"),
                        resultSet.getString("usuarioNombre"),
                        resultSet.getString("usuarioEmail"),
                        resultSet.getInt("Cine_Id"),
                        resultSet.getString("cineNombre"),
                        resultSet.getString("cineTelefono"));
                
                adminCineJoins.add(adminCineJoin);
            }
        }
        return adminCineJoins;
    }
}
