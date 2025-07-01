package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;
    // Método auxiliar para crear un producto de ejemplo - y reutilizarlo en los tests
    private Producto crearProductoEjemplo() {
        Producto producto = new Producto();
        producto.setId("P1");
        producto.setNombre("Producto Ejemplo");
        producto.setDescripcion("Descripción ejemplo");
        producto.setPrecio(new BigDecimal("100.50"));
        producto.setStock(10);
        producto.setCategoria("LIMPIEZA");
        producto.setFechaCreacion(new Date());
        return producto;
    }

   @Test
public void testGetAll() {
    // 1. Configuración del mock (Arrange)
    Producto producto1 = crearProductoEjemplo();
    Producto producto2 = crearProductoEjemplo();
    producto2.setId("P2");
    producto2.setNombre("Otro Producto");

    when(productoRepository.findAll())
        .thenReturn(Arrays.asList(producto1, producto2));

    // 2. Ejecución del método (Act)
    Iterable<Producto> productos = productoService.getAll();

    // 3. Verificación (Assert)
    assertNotNull(productos);

    int count = 0;
    for (Producto p : productos) count++;
    assertEquals(2, count);

    verify(productoRepository, times(1)).findAll();
}


    @Test
    public void testCreate() {
        // Configuración del mock
        Producto nuevoProducto = crearProductoEjemplo();
        when(productoRepository.save(nuevoProducto)).thenReturn(nuevoProducto);

        // Llamada al método del servicio
        Producto guardado = productoService.create(nuevoProducto);

        // Verificaciones
        assertNotNull(guardado);
        assertEquals("Producto Ejemplo", guardado.getNombre());
        assertEquals(10, guardado.getStock());
        
        verify(productoRepository, times(1)).save(nuevoProducto);
    }

    @Test
    public void testActualizarStock() {
        // Configuración del mock
        Producto productoOriginal = crearProductoEjemplo();
        when(productoRepository.findById("P1")).thenReturn(Optional.of(productoOriginal));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al método del servicio
        Producto resultado = productoService.actualizarStock("P1", 15);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(15, resultado.getStock());
        
        verify(productoRepository, times(1)).findById("P1");
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testActualizarStock_ProductoNoEncontrado() {
        // Configuración del mock
        when(productoRepository.findById("P99")).thenReturn(Optional.empty());

        // Llamada al método y verificación
        assertThrows(NullPointerException.class, () -> {
            productoService.actualizarStock("P99", 15);
        });
        
        verify(productoRepository, times(1)).findById("P99");
        verify(productoRepository, never()).save(any());
    }
}