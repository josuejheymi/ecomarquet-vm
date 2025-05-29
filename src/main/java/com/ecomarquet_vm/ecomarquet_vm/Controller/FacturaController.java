package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;
import com.ecomarquet_vm.ecomarquet_vm.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController{
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> getAll(){
        return facturaService.getAll();
    }
    

    @GetMapping("/{id}")
    public Factura getById(@PathVariable Long id) {
        return facturaService.findById(id);
    }

    @PostMapping
    public Factura create(@RequestBody Factura factura) {
        return facturaService.save(factura);
    }

    @PutMapping("/{id}")
    public Factura update(@PathVariable Long id, @RequestBody Factura factura) {
        factura.setId(id);
        return facturaService.save(factura);
    }

    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Long id) {
        facturaService.delete(id);
    }
}
