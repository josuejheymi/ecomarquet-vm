package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompra {
    @Id
    @Column(name = "carrito_id")
    private String id;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;
    
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToMany
    @JoinTable(
        name = "carrito_producto",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    // get
    public List<Producto> getProductos() {
        return productos;
    }

    // set
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}