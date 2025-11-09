/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models.clasificacion;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class Clasificacion {
    private int casificacion_Id;
    private String nombre;
    private int edadMinima;
    private String descripcion;

    public Clasificacion(String nombre, int edadMinima, String descripcion) {
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.descripcion = descripcion;
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
    
    public boolean isValid () {
        return StringUtils.isBlank(nombre) || 
                StringUtils.isBlank(descripcion) || 
                this.edadMinima <= 0;
    }
}
