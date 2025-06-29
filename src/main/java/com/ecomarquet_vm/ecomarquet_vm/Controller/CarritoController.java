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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/carrito")
@Tag( name = "Carrito Compras", description = "Operaciones relacionadas con el carrito de compras")
public class CarritoController {

    @Autowired
    private CarritoCompraService carritoCompraService;

    // agregar productos al carrito de compra
    @PostMapping("/{carritoId}/agregar/{productoId}")
    @Operation( summary = "Agregar producto al carrito", description = "Agrega un producto al carrito de compras")
    public CarritoCompra agregarProducto(@PathVariable String carritoId, @PathVariable String productoId) {
        return carritoCompraService.agregarProducto(carritoId, productoId);
    }
    
    // Eliminar producto del carrito
    @DeleteMapping("/{carritoId}/eliminar/{productoId}")
    @Operation( summary = "Eliminar producto del carrito", description = "Elimina un producto del carrito de compras")
    public CarritoCompra eliminarProducto(@PathVariable String carritoId, @PathVariable String productoId) {
        return carritoCompraService.eliminarProducto(carritoId, productoId);
    }

    // Calcular total del carrito
    @GetMapping("/{carritoId}/total")
    @Operation( summary = "Calcular precio total carrito", description = "Calcula el precio total del carrito de compras")
    public double calcularTotal(@PathVariable String carritoId){
        return carritoCompraService.calcularTotal(carritoId).doubleValue();
    }
}