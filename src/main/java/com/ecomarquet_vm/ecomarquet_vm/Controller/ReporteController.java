package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecomarquet_vm.ecomarquet_vm.Assemblers.ReporteModelAssembler;
import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;
import com.ecomarquet_vm.ecomarquet_vm.Service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Tag(name = "Reportes")
@RequestMapping(value = "/api/reportes", produces = MediaTypes.HAL_JSON_VALUE)
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;
    
    @Autowired
    private ReporteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Lista los reportes")
    public ResponseEntity<?> getAll() {
        try {
            List<Reporte> reportes = reporteService.findAll();
            if (reportes.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204
            }
            return ResponseEntity.ok( // 200
                assembler.toCollectionModel(reportes)
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno"); // 500
        }
    }
   @GetMapping("/{id}")
    @Operation(summary = "Busca reporte por ID")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Reporte reporte = reporteService.findById(id);
            if (reporte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún reporte con ID: " + id); // 404
            }
            // Respuesta exitosa con mensaje personalizado
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Reporte obtenido exitosamente");
            response.put("datos", assembler.toModel(reporte));
            
            return ResponseEntity.ok(response); // 200
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Ocurrió un error al buscar el reporte"); // 500
        }
    }

    @PostMapping
    @Operation(summary = "Crea nuevo reporte")
    public ResponseEntity<?> create(@RequestBody Reporte reporte) {
        try {
                Reporte savedReporte = reporteService.save(reporte);
                return ResponseEntity // 201
                .created(linkTo(methodOn(ReporteController.class).getById(savedReporte.getId())).toUri())
                .body(assembler.toModel(savedReporte));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos inválidos"); // 400
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza reporte")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Reporte reporte) {
        try {
            if (reporteService.findById(id) == null) {
                return ResponseEntity.notFound().build(); // 404
            }
            reporte.setId(id);
            return ResponseEntity.ok(assembler.toModel(reporteService.save(reporte))); // 200
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno"); // 500
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina reporte")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            if (reporteService.findById(id) == null) {
                return ResponseEntity.notFound().build(); // 404
            }
            reporteService.delete(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno"); // 500
        }
    }
}