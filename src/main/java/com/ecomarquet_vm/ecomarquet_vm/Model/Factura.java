package com.ecomarquet_vm.ecomarquet_vm.Model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long Id;

    @Column(nullable = false, name = "fechaFactura")
    private Date fecha;

    @Column(nullable = false, length = 100)
    private String detalles; 

    @Column(nullable = false, length = 100)
    private double total;

    //Relacion con Transaccion
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transaccion", referencedColumnName = "id")
    private Transaccion transaccion;
}

