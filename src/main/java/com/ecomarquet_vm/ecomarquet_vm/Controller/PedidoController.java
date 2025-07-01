package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.PedidoModelAssembler;
import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;
import com.ecomarquet_vm.ecomarquet_vm.Service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Lista todos los pedidos",
               description = "Obtiene todos los pedidos registrados en el sistema")
    public ResponseEntity<?> getAll() {
        try {
            List<Pedido> pedidos = pedidoService.getAll();
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EntityModel<Pedido>> pedidosModel = pedidos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(pedidosModel,
                    linkTo(methodOn(PedidoController.class).getAll()).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al obtener los pedidos");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID",
               description = "Busca un pedido específico por su ID")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        try {
            Optional<Pedido> pedido = pedidoService.obtenerPorId(id);
            if (pedido.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún pedido con ID: " + id);
            }
            EntityModel<Pedido> pedidoModel = assembler.toModel(pedido.get());

            return ResponseEntity.ok(pedidoModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar el pedido");
        }
    }

    @PostMapping
    @Operation(summary = "Crear nuevo pedido",
               description = "Crea un nuevo pedido con los datos proporcionados")
    public ResponseEntity<?> guardar(@RequestBody Pedido pedido) {
        try {
            Pedido pedidoGuardado = pedidoService.guardar(pedido);
            EntityModel<Pedido> pedidoModel = assembler.toModel(pedidoGuardado);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(pedidoModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Datos inválidos para crear el pedido");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido",
               description = "Elimina un pedido específico por su ID")
    public ResponseEntity<?> eliminarPorId(@PathVariable String id) {
        try {
            Optional<Pedido> pedido = pedidoService.obtenerPorId(id);
            if (pedido.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún pedido con ID: " + id);
            }
            pedidoService.eliminarPorId(id);
            return ResponseEntity.ok("Pedido eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al eliminar el pedido");
        }
    }

    @GetMapping("/buscar/estado")
    @Operation(summary = "Buscar pedidos por estado",
               description = "Busca todos los pedidos que coinciden con el estado dado")
    public ResponseEntity<?> buscarPorEstado(@RequestParam String estado) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPorEstado(estado);
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EntityModel<Pedido>> pedidosModel = pedidos.stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(pedidosModel,
                    linkTo(methodOn(PedidoController.class).buscarPorEstado(estado)).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar pedidos por estado");
        }
    }

    @GetMapping("/buscar/fecha")
    @Operation(summary = "Buscar pedidos por fecha",
               description = "Busca todos los pedidos realizados en la fecha indicada")
    public ResponseEntity<?> buscarPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPorFecha(fecha);
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EntityModel<Pedido>> pedidosModel = pedidos.stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(pedidosModel,
                    linkTo(methodOn(PedidoController.class).buscarPorFecha(fecha)).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar pedidos por fecha");
        }
    }

    @GetMapping("/buscar/direccion")
    @Operation(summary = "Buscar pedidos por dirección",
               description = "Busca todos los pedidos que coinciden con la dirección dada")
    public ResponseEntity<?> buscarPorDireccion(@RequestParam String direccion) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPorDireccion(direccion);
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EntityModel<Pedido>> pedidosModel = pedidos.stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(pedidosModel,
                    linkTo(methodOn(PedidoController.class).buscarPorDireccion(direccion)).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar pedidos por dirección");
        }
    }
}
