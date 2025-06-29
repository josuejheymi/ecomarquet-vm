package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @Column(name = "reporte_id")
    private String id;
    
    @Column(nullable = false, length = 100)
    private String tipo;
    
    @Column(nullable = false, name = "fecha_generacion")
    private Date fechaGeneracion;
    
    @Column(nullable = false, length = 500)
    private String datos;
    
    @OneToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    public String getDatos() {
        return datos;
    }
    public void setDatos(String datos) {
        this.datos = datos;
    }
    public Transaccion getTransaccion() {
        return transaccion;
    }
    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
}