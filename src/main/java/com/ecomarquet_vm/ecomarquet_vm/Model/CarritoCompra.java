package com.ecomarquet_vm.ecomarquet_vm.Model;
import java.util.ArrayList;
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
@Table(name = "carritoCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarritoCompra {

    // id
    @Id
    @Column(name = "carrito_id", length = 20)
    private String carrito_id;

    @Column(name = "producto_id", nullable = false, length = 20)
    private String producto_id;

    // Relacion con Producto
    @ManyToMany
    private List<Producto> productos = new ArrayList<>();

    // Total
    @Column(nullable = false, precision = 10) 
    private Double total;

    // Getter
    public List<Producto> getProductos() {
        return productos;
    }

    // Setter
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
