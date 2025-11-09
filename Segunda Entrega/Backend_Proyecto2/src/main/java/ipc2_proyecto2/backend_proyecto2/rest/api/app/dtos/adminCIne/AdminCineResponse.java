/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.adminCIne;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine.AdminCineJoin;

/**
 *
 * @author helder
 */
public class AdminCineResponse {

    private int usuario_Id;
    private String nombreUsuario;
    private String email;
    private int cine_Id;
    private String nombreCine;
    private String telefono;

    public AdminCineResponse(AdminCineJoin adminCineJoin) {
        this.cine_Id = adminCineJoin.getCine_Id();
        this.email = adminCineJoin.getEmail();
        this.nombreCine = adminCineJoin.getNombreCine();
        this.nombreUsuario = adminCineJoin.getNombreUsuario();
        this.telefono = adminCineJoin.getTelefono();
        this.usuario_Id = adminCineJoin.getUsuario_id();
    }

    public int getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(int usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCine_Id() {
        return cine_Id;
    }

    public void setCine_Id(int cine_Id) {
        this.cine_Id = cine_Id;
    }

    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
