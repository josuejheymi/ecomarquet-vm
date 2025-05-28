package com.ecomarquet_vm.ecomarquet_vm.Model;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "carritoCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarritoCompra {

@Id
private String carrito_id;

// Relacion con Producto
@ManyToMany
@JoinColumn(name = "producto_id", nullable= false)
private Producto producto;
// Relacion con Producto (lista de productos)
@ManyToMany
private List<Producto> productos = new ArrayList<>();

@Column(nullable = false, precision = 10) private Double total;

}
