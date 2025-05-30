package com.ecomarquet_vm.ecomarquet_vm.Model;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    
    @Id
    private String id_reporte;
    
    @Column(nullable = false, length = 100)
    private String tipo;
    
    @Column(nullable = false, length = 500)
    private Date fechaGeneracion;
    
    @Column(nullable = false, length = 500)
    private String datos;
    //relacion con Producto
    //@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    //private Producto producto;
}
