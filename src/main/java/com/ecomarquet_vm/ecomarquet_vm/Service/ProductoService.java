package com.ecomarquet_vm.ecomarquet_vm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto actualizarStock(String productoId, Integer nuevoStock){
        Producto producto = productoRepository.findById(productoId).orElse(null);
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);

    }

    
}
