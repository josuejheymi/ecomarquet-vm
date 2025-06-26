package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import com.ecomarquet_vm.ecomarquet_vm.Service.CarritoCompraService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoCompraService carritoCompraService;

    // agregar productos al carrito de compra
    @PostMapping("/{carritoId}/agregar/{productoId}")
    public CarritoCompra agregarProducto(@PathVariable String carritoId, @PathVariable String productoId) {
        return carritoCompraService.agregarProducto(carritoId, productoId);
    }
    
    // Eliminar producto del carrito
    @DeleteMapping("/{carritoId}/eliminar/{productoId}")
    public CarritoCompra eliminarProducto(@PathVariable String carritoId, @PathVariable String productoId) {
        return carritoCompraService.eliminarProducto(carritoId, productoId);
    }

    // Calcular total del carrito - se cambio a bigdecimal para manejar mejor los decimales y evitar errores de redondeo
    @GetMapping("/{carritoId}/total")
    public BigDecimal calcularTotal(@PathVariable String carritoId){
        return carritoCompraService.calcularTotal(carritoId);
    }
}