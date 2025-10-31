/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class Usuario {
    private int Usuario_Id;
    private String Nombre;
    private String Email;
    private String Contraseña;
    private String Rol;

    public Usuario(String Nombre, String Email, String Contraseña, String Rol) {
        this.Nombre = Nombre;
        this.Email = Email;
        this.Contraseña = incriptar(Contraseña);
        this.Rol = Rol;
        this.Usuario_Id = 1;
    }

    public int getUsuario_Id() {
        return Usuario_Id;
    }

    public void setUsuario_Id(int Usuario_Id) {
        this.Usuario_Id = Usuario_Id;
    }

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
    
    public boolean idValid() {
        return StringUtils.isBlank(Nombre) &&
                StringUtils.isBlank(Email) &&
                StringUtils.isBlank(Contraseña) &&
                StringUtils.isBlank(Rol) &&
                Usuario_Id > 0;
                
    }

    private String incriptar(String Contraseña) {
        byte[] message = Contraseña.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }
}
