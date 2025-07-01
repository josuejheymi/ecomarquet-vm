package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import com.ecomarquet_vm.ecomarquet_vm.Controller.CuponController;
import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CuponModelAssembler implements RepresentationModelAssembler<Cupon, EntityModel<Cupon>> {

    @Override
    public EntityModel<Cupon> toModel(Cupon cupon) {
        return EntityModel.of(cupon,
                linkTo(methodOn(CuponController.class).getById(cupon.getId())).withSelfRel(),
                linkTo(methodOn(CuponController.class).getAll()).withRel("cupones"),
                linkTo(methodOn(CuponController.class).delete(cupon.getId())).withRel("eliminar"),
                linkTo(methodOn(CuponController.class).update(cupon.getId(), cupon)).withRel("actualizar")
        );
    }
}
