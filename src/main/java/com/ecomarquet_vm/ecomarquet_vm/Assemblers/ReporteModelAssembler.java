package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;
import com.ecomarquet_vm.ecomarquet_vm.Controller.ReporteController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<Reporte, EntityModel<Reporte>> {

    @Override
    public EntityModel<Reporte> toModel(Reporte reporte) {
        return EntityModel.of(reporte,
            linkTo(methodOn(ReporteController.class).getById(reporte.getId())).withSelfRel(),
            linkTo(methodOn(ReporteController.class).getAll()).withRel("reportes")      
        );
    }
}
//assemblers para la insersion de los links HATEOS