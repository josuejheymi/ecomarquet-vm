package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @Column(name = "usuario_id")
    private String id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String contraseña;
    
    @Column(nullable = false, length = 15)
    private String rol;
    
    @Column(nullable = false, name = "fecha_creacion")
    private Date fechaCreacion;
    
    @Column(nullable = false, length = 15)
    private String activo;
    
    @OneToOne(mappedBy = "usuario")
    private CarritoCompra carrito;
    
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    // Getters and Setters
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getActivo() {
        return activo;
    }
    public void setActivo(String activo) {
        this.activo = activo;
    }
    public CarritoCompra getCarrito() {
        return carrito;
    }
    public void setCarrito(CarritoCompra carrito) {
        this.carrito = carrito;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
}