/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;

/**
 *
 * @author helder
 */
public class UsuarioResponse {
    private int Usuario_Id;
    private String Nombre;
    private String Email;
    private String Rol;
    private float Saldo;

    public UsuarioResponse(Usuario usuario) {
        this.Usuario_Id = usuario.getUsuario_Id();
        this.Nombre = usuario.getNombre();
        this.Email = usuario.getEmail();
        this.Rol = usuario.getRol();
        this.Saldo = usuario.getSaldo();
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

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }
    
    
}
