package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Transaccion {
    @Id
    @Column(name = "transaccion_id")
    private String id;
    
    @Column(nullable = false)
    private Date fecha;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    
    @Column(nullable = false, length = 50)
    private String estado;
    
    @Column(nullable = false, name = "metodo_pago")
    private String metodoPago;
    
    @OneToOne(mappedBy = "transaccion")
    private Pedido pedido;
    
    @OneToOne(mappedBy = "transaccion")
    private Factura factura;
    
    @OneToMany(mappedBy = "transaccion")
    private List<Cupon> cupones;
    
    @OneToOne(mappedBy = "transaccion")
    private Reporte reporte;
}