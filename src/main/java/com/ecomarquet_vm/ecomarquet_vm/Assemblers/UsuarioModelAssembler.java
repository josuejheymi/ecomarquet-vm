package com.ecomarquet_vm.ecomarquet_vm.Assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ecomarquet_vm.ecomarquet_vm.Controller.UsuarioController;
import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(
            usuario,
            linkTo(methodOn(UsuarioController.class).getUsuario(usuario.getId())).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).getAll()).withRel("usuarios")
        );
    }
}
