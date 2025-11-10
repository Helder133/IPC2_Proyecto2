/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion;

/**
 *
 * @author helder
 */
public class ClasificacionRequest {
    private int clasificacion_Id;        
    private String nombre;
    private int edadMinima;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getClasificacion_Id() {
        return clasificacion_Id;
    }

    public void setClasificacion_Id(int clasificacion_Id) {
        this.clasificacion_Id = clasificacion_Id;
    }
    
}
