/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine;

/**
 *
 * @author helder
 */
public class AdminCineJoin {
    private int usuario_id;
    private String nombreUsuario;
    private String email;
    private int cine_Id;
    private String nombreCine;
    private String telefono;

    public AdminCineJoin(int usuario_id, String nombreUsuario, String email, int cine_Id, String nombreCine, String telefono) {
        this.usuario_id = usuario_id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.cine_Id = cine_Id;
        this.nombreCine = nombreCine;
        this.telefono = telefono;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
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
