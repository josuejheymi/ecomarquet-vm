package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.ProductoModelAssembler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import jakarta.persistence.Entity;


@RestController
@RequestMapping("/api/v2/productos")
@Tag( name = "Productos V2", description = "Operaciones relacionadas con los productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler productoModelAssembler;
    
    // Actualizar stock de un producto
    @PutMapping(value="/{id}/actualizar",produces = MediaTypes.HAL_JSON_VALUE)
    @Operation( summary = "Actualizar stock", description = "Actualiza el stock de productos")
    public CollectionModel<EntityModel<Producto>> actualizarStock(@PathVariable String id, @RequestParam Integer stock) {
        productoService.actualizarStock(id, stock);
        List<EntityModel<Producto>> productos = StreamSupport.stream(productoService.getAll().spliterator(), false)
            .map(productoModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos, linkTo(methodOn(ProductoControllerV2.class).actualizarStock(id, stock)).withSelfRel());
    }

    // Obtener todos los productos
    @GetMapping(value="/listaproductos",produces = MediaTypes.HAL_JSON_VALUE)
    @Operation( summary = "Obtener todos los productos", description = "Obtiene una lista de todos los productos")
    public CollectionModel<EntityModel<Producto>> getAll() {
        List<EntityModel<Producto>> productos = StreamSupport.stream(productoService.getAll().spliterator(), false)
            .map(productoModelAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            linkTo(methodOn(ProductoControllerV2.class).getAll()).withSelfRel());
    }
}