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
    private int usuario_Id;
    private String nombre;
    private String email;
    private String contraseña;
    private String rol;
    private float saldo;

    public Usuario(String Nombre, String Email, String Contraseña, String Rol) {
        this.nombre = Nombre;
        this.email = Email;
        this.contraseña = incriptar(Contraseña);
        this.rol = Rol;
        this.usuario_Id = 1;
    }

    public int getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(int usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public boolean isValid() {
        return StringUtils.isBlank(nombre) &&
                StringUtils.isBlank(email) &&
                StringUtils.isBlank(contraseña) &&
                StringUtils.isBlank(rol) &&
                usuario_Id > 0;
                
    }

    private String incriptar(String Contraseña) {
        byte[] message = Contraseña.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }
}
