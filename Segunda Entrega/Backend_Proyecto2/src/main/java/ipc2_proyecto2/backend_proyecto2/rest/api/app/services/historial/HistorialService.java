/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.historial;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.HistorialDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.carteraDigital.Cartera_Digital;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.historial.Historial;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.historial.HistorialTypeEnum;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author helder
 */
public class HistorialService {
    
    public void createHistorial (Cartera_Digital cartera_Digital) throws SQLException {
        HistorialDB historialDB = new HistorialDB();
        Historial historial = new Historial(
                cartera_Digital.getCartera_Digital_Id(),
                LocalDate.now(),
                HistorialTypeEnum.CREACION,
                "Se creo el historial de la cartera digital"
        );
        historialDB.insert(historial);
    }
    
}
