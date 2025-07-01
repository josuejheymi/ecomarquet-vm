package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.FacturaModelAssembler;
import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;
import com.ecomarquet_vm.ecomarquet_vm.Service.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Facturas", description = "Operaciones relacionadas con facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Lista todas las facturas",
               description = "Obtiene todas las facturas registradas en el sistema")
    public ResponseEntity<?> getAllFacturas() {
        try {
            List<Factura> facturas = facturaService.getAllFacturas();
            if (facturas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EntityModel<Factura>> facturasModel = facturas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

            return ResponseEntity.ok(
                CollectionModel.of(facturasModel,
                    linkTo(methodOn(FacturaController.class).getAllFacturas()).withSelfRel()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al obtener las facturas");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar factura por ID",
               description = "Busca una factura específica por su ID")
    public ResponseEntity<?> getFacturaById(@PathVariable String id) {
        try {
            Optional<Factura> factura = facturaService.getFacturaById(id);
            if (factura.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ninguna factura con ID: " + id);
            }
            EntityModel<Factura> facturaModel = assembler.toModel(factura.get());

            return ResponseEntity.ok(facturaModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al buscar la factura");
        }
    }

    @PostMapping
    @Operation(summary = "Crear nueva factura",
               description = "Crea una nueva factura con los datos proporcionados")
    public ResponseEntity<?> createFactura(@RequestBody Factura factura) {
        try {
            Factura nuevaFactura = facturaService.saveFactura(factura);
            EntityModel<Factura> facturaModel = assembler.toModel(nuevaFactura);
            return ResponseEntity
                    .created(facturaModel.getRequiredLink("self").toUri())
                    .body(facturaModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Datos inválidos para crear la factura");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar factura",
               description = "Actualiza una factura existente identificada por su ID")
    public ResponseEntity<?> updateFactura(@PathVariable String id, @RequestBody Factura facturaDetails) {
        try {
            Factura facturaActualizada = facturaService.updateFactura(id, facturaDetails);
            if (facturaActualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ninguna factura con ID: " + id);
            }
            EntityModel<Factura> facturaModel = assembler.toModel(facturaActualizada);
            return ResponseEntity.ok(facturaModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al actualizar la factura");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura",
               description = "Elimina una factura específica por su ID")
    public ResponseEntity<?> deleteFactura(@PathVariable String id) {
        try {
            Optional<Factura> factura = facturaService.getFacturaById(id);
            if (factura.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ninguna factura con ID: " + id);
            }
            facturaService.deleteFactura(id);
            return ResponseEntity.ok("Factura eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error interno al eliminar la factura");
        }
    }
}
