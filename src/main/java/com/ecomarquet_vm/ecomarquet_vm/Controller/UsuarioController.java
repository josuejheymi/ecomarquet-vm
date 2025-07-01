package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
import com.ecomarquet_vm.ecomarquet_vm.Service.UsuarioService;
import com.ecomarquet_vm.ecomarquet_vm.Assemblers.UsuarioModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Tag(name="Usuarios", description="Operaciones relacionadas con usuarios")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    @Operation(summary="Lista de los usuarios", description="Muestra la lista de todos los usuarios en el sistema")    
    public CollectionModel<EntityModel<Usuario>> getAll() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
            .map(usuarioModelAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            usuarios, 
            linkTo(methodOn(UsuarioController.class).getAll()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary="Busca el usuario mediante su ID", description="Busca usuario en la base de datos mediante su ID")
    public ResponseEntity<EntityModel<Usuario>> getUsuario(@PathVariable String id) {
        return usuarioService.findById(id)
            .map(usuarioModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping    
    @Operation(summary="Crea nuevo usuario", description="Crea un nuevo usuario en la base de datos")
    public ResponseEntity<EntityModel<Usuario>> create(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        EntityModel<Usuario> usuarioModel = usuarioModelAssembler.toModel(nuevoUsuario);

        return ResponseEntity
            .created(usuarioModel.getRequiredLink("self").toUri())
            .body(usuarioModel);
    }

    @PutMapping("/{id}")
    @Operation(summary="Actualiza usuario", description="Actualiza al usuario mediante su ID en la base de datos")
    public ResponseEntity<EntityModel<Usuario>> update(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.save(usuario);
        EntityModel<Usuario> usuarioModel = usuarioModelAssembler.toModel(usuarioActualizado);

        return ResponseEntity.ok(usuarioModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Elimina usuario", description="Elimina el usuario mediante su ID en la base de datos")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(usuarioService.findById(id).isPresent()) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
