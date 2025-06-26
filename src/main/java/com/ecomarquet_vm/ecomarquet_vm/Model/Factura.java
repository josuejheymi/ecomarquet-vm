package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @Column(name = "factura_id")
    private String id;
    
    @Column(nullable = false)
    private Date fecha;
    
    @Column(nullable = false, length = 100)
    private String detalles;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    
    @OneToOne
    @JoinColumn(name = "transaccion_id", nullable = false, unique = true)
    private Transaccion transaccion;
}
