package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tipos")
public class TransaccionController {
@Autowired
private TransaccionService TransaccionService;
@GetMapping
public List<Transaccion> getAll() {
return TransaccionService.findAll();
}
@GetMapping("/{id}")
public Transaccion getById(@PathVariable Long id) {
return TransaccionService.findById(id);
}
@PostMapping
public Transaccion create(@RequestBody Transaccion Transaccion) {
return TransaccionService.save(Transaccion);
}
@PutMapping("/{id}")
public Transaccion update(@PathVariable Long id, @RequestBody Transaccion Transaccion) {
Transaccion.setId(id);
return TransaccionService.save(Transaccion);
}
@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
TransaccionService.delete(id);
@PostMapping("/generar/{id}")
public Factura generarFactura(@PathVariable Long id) {
return FacturaService.crearFacturaDesdeTransaccion(id);
}
}
