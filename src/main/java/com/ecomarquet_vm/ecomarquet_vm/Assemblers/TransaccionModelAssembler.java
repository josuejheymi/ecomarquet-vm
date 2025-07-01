package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Controller.TransaccionController;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TransaccionModelAssembler implements RepresentationModelAssembler<Transaccion, EntityModel<Transaccion>> {

    @Override
    public EntityModel<Transaccion> toModel(Transaccion transaccion) {
        return EntityModel.of(transaccion,
            linkTo(methodOn(TransaccionController.class).getById(transaccion.getId())).withSelfRel(),
            linkTo(methodOn(TransaccionController.class).getAll()).withRel("transacciones"),
            linkTo(methodOn(TransaccionController.class).delete(transaccion.getId())).withRel("eliminar")
        );
    }
}
