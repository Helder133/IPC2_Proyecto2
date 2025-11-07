/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.UsuarioTypeEnum;

/**
 *
 * @author helder
 */
public class UpdateUserRequest {
    private String nombre;
    private String email;
    private String contraseña;
    private UsuarioTypeEnum usuarioTypeEnum;

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

    public UsuarioTypeEnum getUsuarioTypeEnum() {
        return usuarioTypeEnum;
    }

    public void setUsuarioTypeEnum(UsuarioTypeEnum usuarioTypeEnum) {
        this.usuarioTypeEnum = usuarioTypeEnum;
    }
    
    
    
}