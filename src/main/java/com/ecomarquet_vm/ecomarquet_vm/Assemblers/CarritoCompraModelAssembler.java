package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import com.ecomarquet_vm.ecomarquet_vm.Controller.CarritoCompraControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CarritoCompraModelAssembler implements RepresentationModelAssembler<CarritoCompra, EntityModel<CarritoCompra>> {

    @Override
    public EntityModel<CarritoCompra> toModel(CarritoCompra carritoCompra) {
        String carritoId = carritoCompra.getId();
        String productoId = carritoCompra.getProductos().isEmpty() ? "0" : carritoCompra.getProductos().get(0).getId();
        return EntityModel.of(
            carritoCompra,
            linkTo(methodOn(CarritoCompraControllerV2.class).getAgregarProducto(carritoId, productoId)).withRel("agregarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).getEliminarProducto(carritoId, productoId)).withRel("eliminarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).getCalcularTotal(carritoId)).withRel("calcularTotal")
        );
    }
}

//linkTo(methodOn(ProductoControllerV2.class).actualizarStock(producto.getId(), producto.getStock())).withRel("actualizarStock")