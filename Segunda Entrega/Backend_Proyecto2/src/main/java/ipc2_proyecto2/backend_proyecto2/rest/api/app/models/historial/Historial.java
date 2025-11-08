/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.models.historial;

import java.time.LocalDate;

/**
 *
 * @author helder
 */
public class Historial {
    private int historial_Id;
    private int cartera_Digital_Id;
    private LocalDate fecha;
    private HistorialTypeEnum historialTypeEnum;
    private String descripcion;

    public Historial(int cartera_Digital_Id, LocalDate fecha, HistorialTypeEnum historialTypeEnum, String descripcion) {
        this.cartera_Digital_Id = cartera_Digital_Id;
        this.fecha = fecha;
        this.historialTypeEnum = historialTypeEnum;
        this.descripcion = descripcion;
    }

    public int getHistorial_Id() {
        return historial_Id;
    }

    public void setHistorial_Id(int historial_Id) {
        this.historial_Id = historial_Id;
    }

    public int getCartera_Digital_Id() {
        return cartera_Digital_Id;
    }

    public void setCartera_Digital_Id(int cartera_Digital_Id) {
        this.cartera_Digital_Id = cartera_Digital_Id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public HistorialTypeEnum getHistorialTypeEnum() {
        return historialTypeEnum;
    }

    public void setHistorialTypeEnum(HistorialTypeEnum historialTypeEnum) {
        this.historialTypeEnum = historialTypeEnum;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
