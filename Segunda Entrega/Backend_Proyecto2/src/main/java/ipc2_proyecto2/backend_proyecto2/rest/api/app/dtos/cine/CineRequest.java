/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.type.EstadoTypeEnum;
import java.time.LocalDate;

/**
 *
 * @author helder
 */
public class CineRequest {
    private int cine_Id;
    private String nombre;
    private String telefono;
    private String direccion;
    private EstadoTypeEnum estadoTypeEnum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaCreacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EstadoTypeEnum getEstadoTypeEnum() {
        return estadoTypeEnum;
    }

    public void setEstadoTypeEnum(EstadoTypeEnum estadoTypeEnum) {
        this.estadoTypeEnum = estadoTypeEnum;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCine_Id() {
        return cine_Id;
    }

    public void setCine_Id(int cine_Id) {
        this.cine_Id = cine_Id;
    }
    
}
