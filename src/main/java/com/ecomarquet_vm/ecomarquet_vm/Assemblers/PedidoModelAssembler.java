package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Controller.PedidoController;
import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
            linkTo(methodOn(PedidoController.class).obtenerPorId(pedido.getId())).withSelfRel(),
            linkTo(methodOn(PedidoController.class).getAll()).withRel("pedidos"),
            linkTo(methodOn(PedidoController.class).eliminarPorId(pedido.getId())).withRel("eliminar"),
            linkTo(methodOn(PedidoController.class).guardar(pedido)).withRel("actualizar")
        );
    }
}
