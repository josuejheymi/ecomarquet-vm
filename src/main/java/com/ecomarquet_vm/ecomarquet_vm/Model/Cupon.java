package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cupones")
public class Cupon {

    @Id
    @Column(nullable = false, precision = 5, scale = 2)
    private String codigo;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal descuento;

    @Temporal(TemporalType.DATE)
    @Column(name = "valido_hasta", nullable = false)
    private Date validoHasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaccion transaccion;
}