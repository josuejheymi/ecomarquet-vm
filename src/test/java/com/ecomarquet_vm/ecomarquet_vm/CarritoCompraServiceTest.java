package com.ecomarquet_vm.ecomarquet_vm;
import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CarritoCompraRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.CarritoCompraService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarritoCompraServiceTest {

    @Mock
    private CarritoCompraRepository carritoCompraRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CarritoCompraService carritoCompraService;

    private CarritoCompra carritoEjemplo;
    private Producto productoEjemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Arrange: preparar carrito de compra
        carritoEjemplo = new CarritoCompra();
        carritoEjemplo.setId("CART123");
        carritoEjemplo.setProductos(new ArrayList<>());

        // Producto ejemplo con precio
        productoEjemplo = new Producto();
        productoEjemplo.setId("PROD456");
        productoEjemplo.setPrecio(new BigDecimal("25.50"));
    }

    @Test
    void testAgregarProducto() {
        // Arrange
        when(carritoCompraRepository.findById("CART123")).thenReturn(Optional.of(carritoEjemplo));
        when(productoRepository.findById("PROD456")).thenReturn(Optional.of(productoEjemplo));
        when(carritoCompraRepository.save(any(CarritoCompra.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        CarritoCompra resultado = carritoCompraService.agregarProducto("CART123", "PROD456");

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getProductos().size());
        assertEquals("PROD456", resultado.getProductos().get(0).getId());
        verify(carritoCompraRepository, times(1)).findById("CART123");
        verify(productoRepository, times(1)).findById("PROD456");
        verify(carritoCompraRepository, times(1)).save(resultado);
    }

    @Test
    void testEliminarProducto() {
        // Arrange
        carritoEjemplo.getProductos().add(productoEjemplo);
        when(carritoCompraRepository.findById("CART123")).thenReturn(Optional.of(carritoEjemplo));
        when(productoRepository.findById("PROD456")).thenReturn(Optional.of(productoEjemplo));
        when(carritoCompraRepository.save(any(CarritoCompra.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        CarritoCompra resultado = carritoCompraService.eliminarProducto("CART123", "PROD456");

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.getProductos().size());
        verify(carritoCompraRepository, times(1)).findById("CART123");
        verify(productoRepository, times(1)).findById("PROD456");
        verify(carritoCompraRepository, times(1)).save(resultado);
    }

    @Test
    void testCalcularTotal() {
        // Arrange
        Producto p1 = new Producto();
        p1.setId("P1");
        p1.setPrecio(new BigDecimal("10.00"));
        Producto p2 = new Producto();
        p2.setId("P2");
        p2.setPrecio(new BigDecimal("15.50"));

        carritoEjemplo.setProductos(Arrays.asList(p1, p2));

        when(carritoCompraRepository.findById("CART123")).thenReturn(Optional.of(carritoEjemplo));

        // Act
        BigDecimal total = carritoCompraService.calcularTotal("CART123");

        // Assert
        assertEquals(new BigDecimal("25.50"), total);
        verify(carritoCompraRepository, times(1)).findById("CART123");
    }

    @Test
    void testFindAll() {
        // Arrange
        List<CarritoCompra> lista = Collections.singletonList(carritoEjemplo);
        when(carritoCompraRepository.findAll()).thenReturn(lista);

        // Act
        List<CarritoCompra> resultado = carritoCompraService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(carritoCompraRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Existente() {
        // Arrange
        when(carritoCompraRepository.findById("CART123")).thenReturn(Optional.of(carritoEjemplo));

        // Act
        CarritoCompra resultado = carritoCompraService.findById("CART123");

        // Assert
        assertNotNull(resultado);
        assertEquals("CART123", resultado.getId());
        verify(carritoCompraRepository, times(1)).findById("CART123");
    }

    @Test
    void testFindById_NoExistente() {
        // Arrange
        when(carritoCompraRepository.findById("NOEXISTE")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            carritoCompraService.findById("NOEXISTE");
        });
        assertEquals("Carrito no encontrado", ex.getMessage());
        verify(carritoCompraRepository, times(1)).findById("NOEXISTE");
    }
}
