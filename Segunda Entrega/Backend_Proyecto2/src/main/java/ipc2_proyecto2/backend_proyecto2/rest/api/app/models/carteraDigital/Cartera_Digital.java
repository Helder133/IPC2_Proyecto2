/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models.carteraDigital;

/**
 *
 * @author helder
 */
public class Cartera_Digital {
    private int cartera_Digital_Id;
    private int usuario_Id;
    private float saldo;

    public Cartera_Digital(int Usuario_Id, float Saldo) {
        this.usuario_Id = Usuario_Id;
        this.saldo = Saldo;
    }

    public int getCartera_Digital_Id() {
        return cartera_Digital_Id;
    }

    public void setCartera_Digital_Id(int cartera_Digital_Id) {
        this.cartera_Digital_Id = cartera_Digital_Id;
    }

    public int getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(int usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
    
}
