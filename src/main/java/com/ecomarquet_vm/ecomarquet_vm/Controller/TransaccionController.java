package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.TransaccionModelAssembler;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Service.TransaccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/transacciones")
@Tag(name = "Transacciones", description = "Operaciones relacionadas con transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private TransaccionModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Lista todas las transacciones",
               description = "Obtiene todas las transacciones registradas en el sistema")
    public ResponseEntity<?> getAll() {
        try {
            List<Transaccion> transacciones = transaccionService.findAll();
            if (transacciones.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204
            }
            List<EntityModel<Transaccion>> transaccionesModel = transacciones.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(transaccionesModel,
                    linkTo(methodOn(TransaccionController.class).getAll()).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al obtener las transacciones"); // 500
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar transacción por ID",
               description = "Busca una transacción específica por su ID")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Optional<Transaccion> transaccion = transaccionService.findById(id);
            if (transaccion.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ninguna transacción con ID: " + id); // 404
            }
            EntityModel<Transaccion> transaccionModel = assembler.toModel(transaccion.get());

            return ResponseEntity.ok(transaccionModel); // 200
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar la transacción"); // 500
        }
    }

    @PostMapping
    @Operation(summary = "Crear nueva transacción",
               description = "Crea una nueva transacción con los datos proporcionados")
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion) {
        try {
            Transaccion savedTransaccion = transaccionService.save(transaccion);
            EntityModel<Transaccion> transaccionModel = assembler.toModel(savedTransaccion);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(transaccionModel); // 201
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Datos inválidos para crear la transacción"); // 400
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar transacción",
               description = "Elimina una transacción específica por su ID")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            if (transaccionService.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ninguna transacción con ID: " + id); // 404
            }
            transaccionService.delete(id);
            return ResponseEntity.ok("Transacción eliminada con éxito"); // 200
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al eliminar la transacción"); // 500
        }
    }
}
