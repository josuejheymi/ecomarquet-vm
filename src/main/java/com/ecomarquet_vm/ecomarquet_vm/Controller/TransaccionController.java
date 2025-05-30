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
public Transaccion getById(@PathVariable String id) {
return TransaccionService.findById(id);
}
@PostMapping
public Transaccion create(@RequestBody Transaccion Transaccion) {
return TransaccionService.save(Transaccion);
}
@PutMapping("/{id}")
public Transaccion update(@PathVariable String id, @RequestBody Transaccion Transaccion) {
Transaccion.setTransaccion_id(id);
return TransaccionService.save(Transaccion);
}
@DeleteMapping("/{id}")
public void delete(@PathVariable String id) {
TransaccionService.delete(id);
}

}
