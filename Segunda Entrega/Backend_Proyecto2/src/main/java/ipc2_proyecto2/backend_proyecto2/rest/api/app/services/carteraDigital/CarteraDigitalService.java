/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.services.carteraDigital;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.db.Cartera_DigitalDB;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.carteraDigital.Cartera_Digital;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.historial.HistorialService;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author helder
 */
public class CarteraDigitalService {

    public void createCarteraDigital(Usuario usuario) throws SQLException {
        HistorialService historialService = new HistorialService();
        Cartera_Digital cartera_Digital = new Cartera_Digital(usuario.getUsuario_Id(), 0);
        Cartera_DigitalDB cartera_DigitalDB = new Cartera_DigitalDB();
        cartera_DigitalDB.insert(cartera_Digital);     
        Optional<Cartera_Digital> carteraOpt = cartera_DigitalDB.selectById(usuario.getUsuario_Id());
        if (carteraOpt.isEmpty()) {
            throw new SQLException("Error al crear el historial para la cartera digital asociada al usuario en creacio");
        }
        historialService.createHistorial(carteraOpt.get());
    }

}
