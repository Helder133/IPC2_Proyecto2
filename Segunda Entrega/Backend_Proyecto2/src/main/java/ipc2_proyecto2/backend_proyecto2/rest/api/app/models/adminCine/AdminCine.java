/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models.adminCine;

/**
 *
 * @author helder
 */
public class AdminCine {
    private int usuario_Id;
    private int cine_Id;

    public AdminCine(int usuario_Id, int cine_Id) {
        this.usuario_Id = usuario_Id;
        this.cine_Id = cine_Id;
    }

    public int getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(int usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public int getCine_Id() {
        return cine_Id;
    }

    public void setCine_Id(int cine_Id) {
        this.cine_Id = cine_Id;
    }
    
    
}
