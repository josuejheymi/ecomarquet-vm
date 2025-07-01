package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Controller.TransaccionControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TransaccionModelAssembler implements RepresentationModelAssembler<Transaccion, EntityModel<Transaccion>> {

    @Override
    public EntityModel<Transaccion> toModel(Transaccion transaccion) {
        return EntityModel.of(
            transaccion,
            linkTo(methodOn(TransaccionControllerV2.class).getAll()).withRel("MostrarTodasLasTransacciones"),
            linkTo(methodOn(TransaccionControllerV2.class).getById(transaccion.getId())).withRel("EncontrarTransaccionEspecifica"),
            linkTo(methodOn(TransaccionControllerV2.class).create(transaccion)).withRel("CrearTransaccion")
        );
    }
}