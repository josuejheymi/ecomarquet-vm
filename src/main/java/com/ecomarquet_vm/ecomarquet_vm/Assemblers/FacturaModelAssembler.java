package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Controller.FacturaController;
import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FacturaModelAssembler implements RepresentationModelAssembler<Factura, EntityModel<Factura>> {

    @Override
    public EntityModel<Factura> toModel(Factura factura) {
        return EntityModel.of(factura,
            linkTo(methodOn(FacturaController.class).getFacturaById(factura.getId())).withSelfRel(),
            linkTo(methodOn(FacturaController.class).getAllFacturas()).withRel("facturas"),
            linkTo(methodOn(FacturaController.class).deleteFactura(factura.getId())).withRel("eliminar"),
            linkTo(methodOn(FacturaController.class).updateFactura(factura.getId(), factura)).withRel("actualizar")
        );
    }
}
