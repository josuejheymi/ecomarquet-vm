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
    @Operation(
        summary="Listar todos los usuarios", 
        description="Obtiene la lista completa de usuarios registrados en el sistema"
    )    
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
    @Operation(
        summary="Buscar usuario por ID", 
        description="Busca un usuario específico en la base de datos usando su ID"
    )
    public ResponseEntity<EntityModel<Usuario>> getUsuario(@PathVariable String id) {
        return usuarioService.findById(id)
            .map(usuarioModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping    
    @Operation(
        summary="Crear nuevo usuario", 
        description="Agrega un nuevo usuario a la base de datos con la información proporcionada"
    )
    public ResponseEntity<EntityModel<Usuario>> create(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        EntityModel<Usuario> usuarioModel = usuarioModelAssembler.toModel(nuevoUsuario);

        return ResponseEntity
            .created(usuarioModel.getRequiredLink("self").toUri())
            .body(usuarioModel);
    }

    @PutMapping("/{id}")
    @Operation(
        summary="Actualizar usuario", 
        description="Actualiza los datos de un usuario existente identificado por su ID"
    )
    public ResponseEntity<EntityModel<Usuario>> update(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.save(usuario);
        EntityModel<Usuario> usuarioModel = usuarioModelAssembler.toModel(usuarioActualizado);

        return ResponseEntity.ok(usuarioModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary="Eliminar usuario", 
        description="Elimina un usuario de la base de datos utilizando su ID"
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(usuarioService.findById(id).isPresent()) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
