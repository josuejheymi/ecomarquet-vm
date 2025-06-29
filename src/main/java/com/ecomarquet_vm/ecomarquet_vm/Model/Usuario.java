package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
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
}