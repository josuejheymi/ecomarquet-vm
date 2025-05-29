package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tipos")
public class CuponController {
@Autowired
private CuponService CuponService;
@GetMapping
public List<Cupon> getAll() {
return CuponService.findAll();
}
@GetMapping("/{codigo}")
public Cupon getBycodigo(@PathVariable String codigo) {
return CuponService.findBycodigo(codigo);
}
@PostMapping
public Cupon create(@RequestBody Cupon cupon) {
return CuponService.save(Cupon);
}
@PutMapping("/{codigo}")
public Cupon update(@PathVariable String codigo, @RequestBody Cupon cupon) {
Cupon.setcodigo(codigo);
return CuponService.save(Cupon);
}
@DeleteMapping("/{codigo}")
public void delete(@PathVariable String codigo) {
CuponService.delete(codigo);
}
@GetMapping("/reporte/descuentoaplicado/{id}")
public Cupon aplicarDescuento(@PathVariable Long id) {
return CuponService.aplicarDescuento(id);
}
