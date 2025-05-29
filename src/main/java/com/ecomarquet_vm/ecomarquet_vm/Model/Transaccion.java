package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private String estado;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    // Relaciones

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido pedido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura", referencedColumnName = "id")
    private Factura factura;

    @OneToMany
    @JoinColumn(name = "id_transaccion")
    private List<Cupon> cupones;

    @OneToMany
    @JoinColumn(name = "id_transaccion")
    private List<Producto> productos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reporte", referencedColumnName = "id")
    private Reporte reporte;

}
