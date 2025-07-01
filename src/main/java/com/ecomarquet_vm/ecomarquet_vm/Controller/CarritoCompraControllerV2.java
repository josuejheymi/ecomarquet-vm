package com.ecomarquet_vm.ecomarquet_vm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomarquet_vm.ecomarquet_vm.Service.CarritoCompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.CarritoCompraModelAssembler;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v2/carrito")
@Tag( name = "Carrito Compras V2", description = "Operaciones relacionadas con el carrito de compras")
public class CarritoCompraControllerV2 {

    @Autowired
    private CarritoCompraService carritoCompraService;

    @Autowired
    private CarritoCompraModelAssembler carritoCompraModelAssembler;

    // Agregar un producto al carrito de compra
    @PutMapping(value="/{carrito_id}/{producto_id}/agregar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation( summary = "Agregar producto al carrito", description = "Agrega un producto al carrito de compras")
    public CollectionModel<EntityModel<CarritoCompra>> getAgregarProducto(
            @PathVariable("carrito_id") String carritoId,
            @PathVariable("producto_id") String productoId) {
        carritoCompraService.agregarProducto(String.valueOf(carritoId), String.valueOf(productoId));
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getAgregarProducto(carritoId, productoId)).withSelfRel());
    }

    // Eliminar un producto del carrito de compra
    @DeleteMapping(value="/{carrito_id}/{producto_id}/eliminar", produces= MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar producto del carrito", description = "Elimina un producto del carrito de compras")
    public CollectionModel<EntityModel<CarritoCompra>> getEliminarProducto(
            @PathVariable("carrito_id") String carritoId,
            @PathVariable("producto_id") String productoId) {
        carritoCompraService.eliminarProducto(carritoId, productoId);
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getEliminarProducto(carritoId, productoId)).withSelfRel());
    }

    // Calcular el total del carrito de compra
    // BigDecimal para manejar precios
    @GetMapping(value="/{carrito_id}/total",produces = MediaTypes.HAL_JSON_VALUE)
    @Operation( summary = "Calcular precio total carrito", description = "Calcula el precio total del carrito de compras")
    public CollectionModel<EntityModel<CarritoCompra>> getCalcularTotal(@PathVariable("carrito_id") String carritoId) {
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getCalcularTotal(carritoId)).withSelfRel());
    }
}