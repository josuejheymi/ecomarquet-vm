package com.ecomarquet_vm.ecomarquet_vm.Model;
import java.sql.Date;
//import java.util.List;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    private String id_usuario;

    @Column(nullable = false,unique = true,length = 20)
    private String nombre;

    @Column(nullable = false,unique = true,length = 50)
    private String email;

    @Column(nullable = false,unique = true,length = 100)
    private String contraseña;

    @Column(nullable = false, unique = true, length = 15)
    private String rol;

    @Column(nullable = false, unique = true)
    private Date fechaCreacion;

    @Column(nullable = false, length = 15)
    private String activo;

    //Relacion con carrito compra
    @ManyToOne
    @JoinColumn(name= "carrito_id", nullable = false)
    private CarritoCompra carritoCompra;
    //Relacion con pedido
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}
