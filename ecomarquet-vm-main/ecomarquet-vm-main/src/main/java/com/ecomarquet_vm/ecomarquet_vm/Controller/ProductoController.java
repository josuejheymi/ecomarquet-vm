package com.ecomarquet_vm.ecomarquet_vm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/productos")
@Tag( name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Actualizar stock de un producto
    @PutMapping("/{id}/stock")
    @Operation( summary = "Actualizar stock", description = "Actualiza el stock de productos")
    public Producto actualizarStock(@PathVariable String id, @RequestParam Integer stock) {
        return productoService.actualizarStock(id, stock);
    }
}
