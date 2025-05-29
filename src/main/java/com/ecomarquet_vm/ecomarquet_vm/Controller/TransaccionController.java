package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

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
public Transaccion getById(@PathVariable String transaccion_id) {
return TransaccionService.findById(transaccion_id);
}
@PostMapping
public Transaccion create(@RequestBody Transaccion Transaccion) {
return TransaccionService.save(Transaccion);
}
@PutMapping("/{id}")
public Transaccion update(@PathVariable String transaccion_id, @RequestBody Transaccion Transaccion) {
Transaccion.setId(transaccion_id);
return TransaccionService.save(Transaccion);
}
@DeleteMapping("/{id}")
public void delete(@PathVariable String transaccion_id) {
TransaccionService.delete(transaccion_id);

@PostMapping("/generar/{id}")
public Factura generarFactura(@PathVariable String transaccion_id) {
return FacturaService.crearFacturaDesdeTransaccion(id);
}
}
}