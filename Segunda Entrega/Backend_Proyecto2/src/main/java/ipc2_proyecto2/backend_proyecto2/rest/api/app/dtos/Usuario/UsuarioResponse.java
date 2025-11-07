/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.usuario.UsuarioTypeEnum;

/**
 *
 * @author helder
 */
public class UsuarioResponse {
    private int usuario_Id;
    private String nombre;
    private String email;
    private UsuarioTypeEnum usuarioTypeEnum;

    public UsuarioResponse(Usuario usuario) {
        this.usuario_Id = usuario.getUsuario_Id();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.usuarioTypeEnum = usuario.getUsuarioTypeEnum();
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

    public UsuarioTypeEnum getUsuarioTypeEnum() {
        return usuarioTypeEnum;
    }

    public void setUsuarioTypeEnum(UsuarioTypeEnum usuarioTypeEnum) {
        this.usuarioTypeEnum = usuarioTypeEnum;
    }

}
