package com.ecomarquet_vm.ecomarquet_vm.Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    // id
    @Id
    @Column(name = "producto_id", length = 20)
    private String producto_id;

    // Nombre
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // Descripcion
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    // Precio
    @Column(name = "precio", nullable = false, precision = 10) 
    private Double precio;

    // Stock
    @Column(name = "stock", nullable = false, precision = 10) 
    private Integer stock;

    // Categoria
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    // Fecha creacion
    @Column(name = "Fecha_Creacion", nullable = false)
    private Date fechaCreacion;

    // Relacion con CarritoCompra
    @ManyToMany(mappedBy = "productos")
    private List<CarritoCompra> carritoCompras = new ArrayList<>();

    // Getter
    public Double getPrecio() {
    return precio;
    }

}
