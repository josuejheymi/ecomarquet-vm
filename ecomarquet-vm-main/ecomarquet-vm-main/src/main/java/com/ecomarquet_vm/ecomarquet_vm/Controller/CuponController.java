package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cupones")  // Cambiado de "/api/tipos" a "/api/cupones" (más descriptivo)
public class CuponController {

    @Autowired
    private CuponService cuponService;  // Corregido: minúscula para seguir convenciones

    // Obtener todos los cupones
    @GetMapping
    public ResponseEntity<List<Cupon>> getAll() {
        return ResponseEntity.ok(cuponService.findAll());
    }

    // Buscar cupón por ID (cupon_id)
    @GetMapping("/id/{id}")
    public ResponseEntity<Cupon> getById(@PathVariable String id) {
        Optional<Cupon> cupon = cuponService.findById(id);
        return cupon.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar cupón por código (codigo)
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Cupon> getByCodigo(@PathVariable String codigo) {
        Cupon cupon = cuponService.findByCodigo(codigo);
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cupon);
    }

    // Crear un nuevo cupón
    @PostMapping
    public ResponseEntity<Cupon> create(@RequestBody Cupon cupon) {
        return ResponseEntity.ok(cuponService.save(cupon));
    }

    // Actualizar un cupón existente
    @PutMapping("/{id}")
    public ResponseEntity<Cupon> update(@PathVariable String id, @RequestBody Cupon cupon) {
        cupon.setId(id);  // Asegurar que el ID del cuerpo coincida con el de la URL
        return ResponseEntity.ok(cuponService.save(cupon));
    }

    // Eliminar un cupón
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cuponService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Aplicar descuento a una transacción (retorna el monto con descuento)
    @GetMapping("/aplicar-descuento/{transaccionId}")
    public ResponseEntity<BigDecimal> aplicarDescuento(@PathVariable String transaccionId) {
        BigDecimal totalConDescuento = cuponService.aplicarDescuento(transaccionId);
        return ResponseEntity.ok(totalConDescuento);
    }

    // Verificar si un cupón es válido (opcional)
    @GetMapping("/validar/{codigoCupon}")
    public ResponseEntity<Boolean> esCuponValido(@PathVariable String codigoCupon) {
        boolean esValido = cuponService.esCuponValido(codigoCupon);
        return ResponseEntity.ok(esValido);
    }
}
