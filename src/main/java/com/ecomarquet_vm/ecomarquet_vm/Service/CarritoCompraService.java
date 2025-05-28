package com.ecomarquet_vm.ecomarquet_vm.Service;
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
    public CarritoCompra agregarProducto(String carritoId, Long productoId) {
        var carrito = carritoCompraRepository.findById(carritoId).orElseThrow(); // Busca el carrito por su ID
        var producto = productoRepository.findById(productoId).orElseThrow(); // Busca el producto por su ID
        carrito.getProductos().add(producto); // Agrega el prducto al carrito
        return carritoCompraRepository.save(carrito); // Guarda el carrito actualizado
    }

    // Eliminar un producto del carrito de compra
    public CarritoCompra eliminarProducto(String carritoId, Long productoId) {
        var carrito = carritoCompraRepository.findById(carritoId).orElseThrow();
        var producto = productoRepository.findById(productoId).orElseThrow();
        carrito.getProductos().remove(producto);
        return carritoCompraRepository.save(carrito);
    }

    // Calcular el total del carrito de compra
    public double calcularTotal() {
    return productos.stream()
            .mapToDouble(Producto::getPrecio)
            .sum();
}
}

// @Autowired inyecta las dependencias de los repositorios
// @Service indica que es un servicio que puede ser utilizado en otras partes de la aplicacion
