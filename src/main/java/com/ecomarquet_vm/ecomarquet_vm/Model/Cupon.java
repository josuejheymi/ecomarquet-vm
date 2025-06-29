package com.ecomarquet_vm.ecomarquet_vm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cupon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {
    @Id
    @Column(name = "cupon_id")
    private String id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;
    
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal descuento;
    
    @Column(nullable = false, name = "valido_hasta")
    private Date validoHasta;
    
    @ManyToOne
    @JoinColumn(name = "transaccion_id", nullable = false)
    private Transaccion transaccion;

    // getter and setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public BigDecimal getDescuento() {
        return descuento;
    }
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    public Date getValidoHasta() {
        return validoHasta;
    }
    public void setValidoHasta(Date validoHasta) {
        this.validoHasta = validoHasta;
    }
    public Transaccion getTransaccion() {
        return transaccion;
    }
}