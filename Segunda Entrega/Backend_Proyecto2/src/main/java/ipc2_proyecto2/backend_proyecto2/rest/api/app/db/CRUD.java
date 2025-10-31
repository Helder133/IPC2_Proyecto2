/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.db;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author helder
 * @param <T>
 */
public interface CRUD <T>{
    T insert(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<T> select() throws SQLException;
    T selectById(int id) throws SQLException;
    List<T> selectByString(String code) throws SQLException;
}
