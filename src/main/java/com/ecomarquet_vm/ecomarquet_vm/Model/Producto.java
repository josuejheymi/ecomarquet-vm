package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @Column(name = "producto_id")
    private String id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String descripcion;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(nullable = false)
    private Integer stock;
    
    @Column(nullable = false, length = 100)
    private String categoria;
    
    @Column(nullable = false, name = "fecha_creacion")
    private Date fechaCreacion;
    
    @ManyToMany(mappedBy = "productos")
    private List<CarritoCompra> carritos;

    // getters and setters
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public List<CarritoCompra> getCarritos() {
        return carritos;
    }
    public void setCarritos(List<CarritoCompra> carritos) {
        this.carritos = carritos;
    }

}