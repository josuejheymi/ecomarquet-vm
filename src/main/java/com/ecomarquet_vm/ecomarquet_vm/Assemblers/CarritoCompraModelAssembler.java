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
    public EntityModel<CarritoCompra> toModel(CarritoCompra carrito) {
        String carritoId = carrito.getId();
        String productoEjemplo = "P1"; // ID de producto de ejemplo o puedes obtener el primero
        
        return EntityModel.of(carrito,
            linkTo(methodOn(CarritoCompraControllerV2.class).obtenerCarrito(carritoId)).withSelfRel(),
            linkTo(methodOn(CarritoCompraControllerV2.class).agregarProducto(carritoId, productoEjemplo)).withRel("agregarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).eliminarProducto(carritoId, productoEjemplo)).withRel("eliminarProducto"),
            linkTo(methodOn(CarritoCompraControllerV2.class).calcularTotal(carritoId)).withRel("calcularTotal")
        );
    }
}
//linkTo(methodOn(ProductoControllerV2.class).actualizarStock(producto.getId(), producto.getStock())).withRel("actualizarStock")
//todos los métodos de la clase CarritoCompraControllerV2 están enlazados a los métodos del controlador, permitiendo una navegación fácil entre las operaciones relacionadas con el carrito de compras. Esto es útil para construir APIs RESTful que sigan las mejores prácticas de HATEOAS (Hypermedia as the Engine of Application State).
