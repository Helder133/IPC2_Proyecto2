/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models;

/**
 *
 * @author helder
 */
public class Cartera_Digital {
    private int Cartera_Digital_Id;
    private int Usuario_Id;
    private float Saldo;

    public Cartera_Digital(int Usuario_Id, float Saldo) {
        this.Usuario_Id = Usuario_Id;
        this.Saldo = Saldo;
    }

    public int getCartera_Digital_Id() {
        return Cartera_Digital_Id;
    }

    public void setCartera_Digital_Id(int Cartera_Digital_Id) {
        this.Cartera_Digital_Id = Cartera_Digital_Id;
    }

    public int getUsuario_Id() {
        return Usuario_Id;
    }

    public void setUsuario_Id(int Usuario_Id) {
        this.Usuario_Id = Usuario_Id;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }
    
    
    
}
