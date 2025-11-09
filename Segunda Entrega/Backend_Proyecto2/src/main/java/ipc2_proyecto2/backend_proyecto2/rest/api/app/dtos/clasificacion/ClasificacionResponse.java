/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.clasificacion.Clasificacion;

/**
 *
 * @author helder
 */
public class ClasificacionResponse {
    
    private int casificacion_Id;
    private String nombre;
    private int edadMinima;
    private String descripcion;

    public ClasificacionResponse(Clasificacion clasificacion) {
        this.casificacion_Id = clasificacion.getCasificacion_Id();
        this.descripcion = clasificacion.getDescripcion();
        this.edadMinima = clasificacion.getEdadMinima();
        this.nombre = clasificacion.getNombre();
    }

    public int getCasificacion_Id() {
        return casificacion_Id;
    }

    public void setCasificacion_Id(int casificacion_Id) {
        this.casificacion_Id = casificacion_Id;
    }

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

}
