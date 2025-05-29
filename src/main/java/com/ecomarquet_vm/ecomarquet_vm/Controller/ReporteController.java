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
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;
    @GetMapping
    public List<Reporte> getAll() {
        return reporteService.findAll();
    }
    @GetMapping("/{id}")
    public Reporte getById(@PathVariable Long id) {
        return reporteService.findById(id);
    }
    @PostMapping
    public Reporte create(@RequestBody Reporte reporte) {
        return reporteService.save(reporte);
    }
    @PutMapping("/{id}")
    public Reporte update(@PathVariable Long id, @RequestBody Reporte reporte) {
        reporte.setId(id); // Ensure the ID is set for the update
        return reporteService.save(reporte);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reporteService.delete(id);
    }
}
