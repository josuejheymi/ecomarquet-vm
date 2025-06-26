package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @Column(name = "reporte_id")
    private String id;
    
    @Column(nullable = false, length = 100)
    private String tipo;
    
    @Column(nullable = false, name = "fecha_generacion")
    private Date fechaGeneracion;
    
    @Column(nullable = false, length = 500)
    private String datos;
    
    @OneToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;
}