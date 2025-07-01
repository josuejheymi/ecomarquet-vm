package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;
import com.ecomarquet_vm.ecomarquet_vm.Service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name="Reportes" , description="Operaciones relacionadas con reportes")
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;
    @GetMapping
    @Operation(summary="Lista los reportes", description="Entrega todos los reportes realizados")
    public List<Reporte> getAll() {
        return reporteService.findAll();
    }
    @GetMapping("/{id}")
    @Operation(summary="Busca reporte", description="Busca el reporte mediante el ID")
    public Reporte getById(@PathVariable String id) {
        return reporteService.findById(id);
    }
    @PostMapping
    @Operation(summary="Crea reporte", description="Crea nuevo reporte en la base de datos")
    public Reporte create(@RequestBody Reporte reporte) {
        return reporteService.save(reporte);
    }
    @PutMapping("/{id}")
    @Operation(summary="Actualiza el reporte", description="Actualiza el reporte en la base de datos mediante el ID")
    public Reporte update(@PathVariable String id, @RequestBody Reporte reporte) {
        reporte.setId(id); // Ensure the ID is set for the update
        return reporteService.save(reporte);
    }
    @DeleteMapping("/{id}")
    @Operation(summary="Elimina el reporte", description="Elimina el reporte de la base de datos mediante su ID")
    public void delete(@PathVariable String id) {
        reporteService.delete(id);
    }
}
