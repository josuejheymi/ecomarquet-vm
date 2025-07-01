package com.ecomarquet_vm.ecomarquet_vm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Actualizar Stock de un producto
    public Producto actualizarStock(String productoId, Integer nuevoStock){
        Producto producto = productoRepository.findById(productoId).orElse(null);
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    // getAll
    public Iterable<Producto> getAll() {
        return productoRepository.findAll();
    }

    // create
    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }
}