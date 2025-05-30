package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;
import com.ecomarquet_vm.ecomarquet_vm.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable String id) {
        Optional<Pedido> pedido = pedidoService.obtenerPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.guardar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable String id) {
        pedidoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<List<Pedido>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(pedidoService.buscarPorEstado(estado));
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<Pedido>> buscarPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ResponseEntity.ok(pedidoService.buscarPorFecha(fecha));
    }

    @GetMapping("/buscar/direccion")
    public ResponseEntity<List<Pedido>> buscarPorDireccion(@RequestParam String direccion) {
        return ResponseEntity.ok(pedidoService.buscarPorDireccion(direccion));
    }
}