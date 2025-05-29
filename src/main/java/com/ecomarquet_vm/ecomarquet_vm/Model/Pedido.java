package com.ecomarquet_vm.ecomarquet_vm.Model;

import java.util.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long Id;

    @Column (name = "fechaPedido")
    private Date fechaPedido;

    @Column (nullable = false, length = 100)
    private String estado;

    @Column (nullable = false, length = 100)
    private String direccionEnvio;

    //Relacion con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    //Relacion con Transaccion
    @ManyToOne
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaccion transaccion;
    
}
