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

    // getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        this.total = this.total.add(producto.getPrecio());
    }
    public void eliminarProducto(Producto producto) {
        if (this.productos.remove(producto)) {
            this.total = this.total.subtract(producto.getPrecio());
        }
    }
    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Producto producto : productos) {
            total = total.add(producto.getPrecio());
        }
        return total;
    }
}