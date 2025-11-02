/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario;

/**
 *
 * @author helder
 */
public class UpdateUserRequest {
    private String Nombre;
    private String Email;
    private String Contraseña;
    private String Rol;
    
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getContraseña() {
        return Contraseña;
    }
    
    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    
    public String getRol() {
        return Rol;
    }
    
    public void setRol(String Rol) {
        this.Rol = Rol;
    }
    
}