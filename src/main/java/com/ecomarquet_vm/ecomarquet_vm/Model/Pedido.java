package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Pedido {
    @Id
    @Column(name = "pedido_id")
    private String id;
    
    @Column(nullable = false, name = "fecha_pedido")
    private Date fechaPedido;
    
    @Column(nullable = false, length = 100)
    private String estado;
    
    @Column(nullable = false, name = "direccion_envio")
    private String direccionEnvio;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @OneToOne
    @JoinColumn(name = "transaccion_id", nullable = false)
    private Transaccion transaccion;
}