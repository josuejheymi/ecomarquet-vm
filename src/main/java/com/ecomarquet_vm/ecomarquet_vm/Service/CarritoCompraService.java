package com.ecomarquet_vm.ecomarquet_vm.Service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CarritoCompraRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;


@Service
public class CarritoCompraService {

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Agregar un producto al carrito de compra
    public CarritoCompra agregarProducto( String carritoId, String productoId) {
        CarritoCompra carrito = carritoCompraRepository.findById(carritoId).orElse(null); // Busca el carrito por su ID
        Producto producto = productoRepository.findById(productoId).orElse(null); // Busca el producto por su ID
        carrito.getProductos().add(producto); // Agrega el prducto al carrito
        return carritoCompraRepository.save(carrito); // Guarda el carrito actualizado
    }

    // Eliminar un producto del carrito de compra
    public CarritoCompra eliminarProducto(String carritoId, String productoId) {
        CarritoCompra carrito = carritoCompraRepository.findById(carritoId).orElse(null);
        Producto producto = productoRepository.findById(productoId).orElse(null);
        carrito.getProductos().remove(producto);
        return carritoCompraRepository.save(carrito);
    }

    // Calcular el total del carrito de compra - Pequeña mejora: se usa BigDecimal para manejar precios
    public BigDecimal calcularTotal(String carritoId) {
    CarritoCompra carrito = carritoCompraRepository.findById(carritoId)
        .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

    return carrito.getProductos().stream()
            .map(Producto::getPrecio) // devuelve BigDecimal
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Método para obtener todos los carritos de compra
    public List<CarritoCompra> findAll() {
        return carritoCompraRepository.findAll();
    }

}

// @Autowired inyecta las dependencias de los repositorios
// @Service indica que es un servicio que puede ser utilizado en otras partes de la aplicacion