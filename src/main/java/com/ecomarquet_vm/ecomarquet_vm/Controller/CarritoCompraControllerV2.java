package com.ecomarquet_vm.ecomarquet_vm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ecomarquet_vm.ecomarquet_vm.Service.CarritoCompraService;
import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/carrito")
@Tag(name = "Carrito Compras V2", description = "Operaciones relacionadas con el carrito de compras")
public class CarritoCompraControllerV2 {

    @Autowired
    private CarritoCompraService carritoCompraService;

    @PutMapping(value = "/{carrito_id}/{producto_id}/agregar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar producto al carrito")
    public EntityModel<CarritoCompra> agregarProducto(
            @PathVariable("carrito_id") String carritoId,
            @PathVariable("producto_id") String productoId) {
        
        CarritoCompra carrito = carritoCompraService.agregarProducto(carritoId, productoId);
        
        return EntityModel.of(carrito,
            linkTo(methodOn(CarritoCompraControllerV2.class).agregarProducto(carritoId, productoId)).withSelfRel(),
            linkTo(methodOn(CarritoCompraControllerV2.class).eliminarProducto(carritoId, productoId)).withRel("eliminarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).calcularTotal(carritoId)).withRel("calcularTotal"),
            linkTo(methodOn(CarritoCompraControllerV2.class).obtenerCarrito(carritoId)).withRel("verCarrito")
        );
    }

    @DeleteMapping(value = "/{carrito_id}/{producto_id}/eliminar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar producto del carrito")
    public EntityModel<CarritoCompra> eliminarProducto(
            @PathVariable("carrito_id") String carritoId,
            @PathVariable("producto_id") String productoId) {
        
        CarritoCompra carrito = carritoCompraService.eliminarProducto(carritoId, productoId);
        
        return EntityModel.of(carrito,
            linkTo(methodOn(CarritoCompraControllerV2.class).eliminarProducto(carritoId, productoId)).withSelfRel(),
            linkTo(methodOn(CarritoCompraControllerV2.class).agregarProducto(carritoId, productoId)).withRel("agregarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).calcularTotal(carritoId)).withRel("calcularTotal"),
            linkTo(methodOn(CarritoCompraControllerV2.class).obtenerCarrito(carritoId)).withRel("verCarrito")
        );
    }

    @GetMapping(value = "/{carrito_id}/total", produces = MediaTypes.HAL_JSON_VALUE)
@Operation(summary = "Calcular precio total carrito")
public EntityModel<Map<String, Object>> calcularTotal(@PathVariable("carrito_id") String carritoId) {
    
    // CAMBIO 1: Se reemplazó el findAll() por un método específico que calcula solo el total del carrito solicitado
    // - Antes: carritoCompraService.findAll() -> devolvía todos los carritos innecesariamente
    // - Ahora: Solo calcula el total del carrito con el ID recibido
    BigDecimal total = carritoCompraService.calcularTotal(carritoId);
    
    // CAMBIO 2: Se usa un Map para la respuesta en lugar de la entidad completa
    // - Ventaja: Proporciona una estructura más limpia y específica
    Map<String, Object> response = new HashMap<>();
    response.put("carritoId", carritoId);  // Se añade el ID para contexto
    response.put("total", total);          // El dato principal solicitado
    
    // CAMBIO 3: Generación mejorada de enlaces HATEOAS
    return EntityModel.of(response,
        // Enlace self (al propio endpoint)
        linkTo(methodOn(CarritoCompraControllerV2.class).calcularTotal(carritoId)).withSelfRel(),
        
        // Enlaces a acciones relacionadas (mejor descubribilidad de la API)
        linkTo(methodOn(CarritoCompraControllerV2.class).agregarProducto(carritoId, "P1")).withRel("agregarProducto"),
        linkTo(methodOn(CarritoCompraControllerV2.class).eliminarProducto(carritoId, "P1")).withRel("eliminarProducto"),
        linkTo(methodOn(CarritoCompraControllerV2.class).obtenerCarrito(carritoId)).withRel("verCarrito")
    );
}

    @GetMapping(value = "/{carrito_id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener carrito por ID")
    public EntityModel<CarritoCompra> obtenerCarrito(@PathVariable("carrito_id") String carritoId) {
        CarritoCompra carrito = carritoCompraService.findById(carritoId);
        
        return EntityModel.of(carrito,
            linkTo(methodOn(CarritoCompraControllerV2.class).obtenerCarrito(carritoId)).withSelfRel(),
            linkTo(methodOn(CarritoCompraControllerV2.class).agregarProducto(carritoId, "P1")).withRel("agregarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).eliminarProducto(carritoId, "P1")).withRel("eliminarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).calcularTotal(carritoId)).withRel("calcularTotal")
        );
}
}