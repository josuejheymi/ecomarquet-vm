package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Controller.ProductoControllerV2;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(
            producto,
            linkTo(methodOn(ProductoControllerV2.class).getAll()).withSelfRel(),
            linkTo(methodOn(ProductoControllerV2.class).actualizarStock(producto.getId(), producto.getStock())).withRel("actualizarStock")
        );
    }
}
