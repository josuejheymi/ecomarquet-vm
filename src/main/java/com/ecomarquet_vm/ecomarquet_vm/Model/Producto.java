package com.ecomarquet_vm.ecomarquet_vm.Model;
import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    // id
    @Id
    private String carrito_id;

    // Nombre
    @Column(nullable = false, length = 100)
    private String nombre;

    // Descripcion
    @Column(nullable = false, length = 100)
    private String descripcion;

    // Precio
    @Column(nullable = false, precision = 10) private Double precio;

    // Stock
    @Column(nullable = false, precision = 10) private Integer stock;

    // Categoria
    @Column(nullable = false, length = 100)
    private String categoria;

    // Fecha creacion
    @Column(name = "Fecha_Creacion", nullable = false)
    private Date fechaCreacion;

    // Relacion con CarritoCompra
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<CarritoCompra> carritoCompras;

}
