package com.ecomarquet_vm.ecomarquet_vm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomarquet_vm.ecomarquet_vm.Service.CarritoCompraService;
import com.ecomarquet_vm.ecomarquet_vm.Assemblers.CarritoCompraModelAssembler;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoCompraControllerV2 {

    @Autowired
    private CarritoCompraService carritoCompraService;

    @Autowired
    private CarritoCompraModelAssembler carritoCompraModelAssembler;

    // Agregar un producto al carrito de compra
    @GetMapping(path = "/listar-agregados",produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<CarritoCompra>> getAgregarProducto(){
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getAgregarProducto()).withSelfRel());
    }

    // Eliminar un producto del carrito de compra
    @GetMapping(path = "/listar-eliminados",produces= MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<CarritoCompra>> getEliminarProducto(){
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getEliminarProducto()).withSelfRel());
    }

    // Calcular el total del carrito de compra
    // BigDecimal para manejar precios
    @GetMapping(path = "/listar-totales",produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<CarritoCompra>> getCalcularTotal(){
        List<EntityModel<CarritoCompra>> carritos = carritoCompraService.findAll().stream()
            .map(carritoCompraModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoCompraControllerV2.class).getCalcularTotal()).withSelfRel());
    }
}