package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
import com.ecomarquet_vm.ecomarquet_vm.Repository.PedidoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.PedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private Date fecha;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Usuario usuario = new Usuario();
        usuario.setId("user123");

        Transaccion transaccion = new Transaccion();
        transaccion.setId("tx123");

        fecha = new Date();

        pedido = new Pedido();
        pedido.setId("p001");
        pedido.setFechaPedido(fecha);
        pedido.setEstado("Enviado");
        pedido.setDireccionEnvio("Av. Central 123");
        pedido.setUsuario(usuario);
        pedido.setTransaccion(transaccion);
    }

    @Test
    public void testGetAll() {
        // Arrange
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        // Act
        List<Pedido> result = pedidoService.getAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("p001", result.get(0).getId());
        verify(pedidoRepository).findAll();
    }

    @Test
    public void testObtenerPorId_Existe() {
        // Arrange
        when(pedidoRepository.findById("p001")).thenReturn(Optional.of(pedido));

        // Act
        Optional<Pedido> result = pedidoService.obtenerPorId("p001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Enviado", result.get().getEstado());
        verify(pedidoRepository).findById("p001");
    }

    @Test
    public void testObtenerPorId_NoExiste() {
        // Arrange
        when(pedidoRepository.findById("noexiste")).thenReturn(Optional.empty());

        // Act
        Optional<Pedido> result = pedidoService.obtenerPorId("noexiste");

        // Assert
        assertFalse(result.isPresent());
        verify(pedidoRepository).findById("noexiste");
    }

    @Test
    public void testGuardar() {
        // Arrange
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        // Act
        Pedido result = pedidoService.guardar(pedido);

        // Assert
        assertNotNull(result);
        assertEquals("p001", result.getId());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    public void testEliminarPorId() {
        // Arrange
        doNothing().when(pedidoRepository).deleteById("p001");

        // Act
        pedidoService.eliminarPorId("p001");

        // Assert
        verify(pedidoRepository).deleteById("p001");
    }

    @Test
    public void testBuscarPorEstado() {
        // Arrange
        when(pedidoRepository.buscarPorEstado("Enviado")).thenReturn(List.of(pedido));

        // Act
        List<Pedido> result = pedidoService.buscarPorEstado("Enviado");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Enviado", result.get(0).getEstado());
        verify(pedidoRepository).buscarPorEstado("Enviado");
    }

    @Test
    public void testBuscarPorFecha() {
        // Arrange
        when(pedidoRepository.buscarPorFechaPedido(fecha)).thenReturn(List.of(pedido));

        // Act
        List<Pedido> result = pedidoService.buscarPorFecha(fecha);

        // Assert
        assertEquals(1, result.size());
        assertEquals(fecha, result.get(0).getFechaPedido());
        verify(pedidoRepository).buscarPorFechaPedido(fecha);
    }

    @Test
    public void testBuscarPorDireccion() {
        // Arrange
        when(pedidoRepository.buscarPorDireccionEnvio("Av. Central 123")).thenReturn(List.of(pedido));

        // Act
        List<Pedido> result = pedidoService.buscarPorDireccion("Av. Central 123");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Av. Central 123", result.get(0).getDireccionEnvio());
        verify(pedidoRepository).buscarPorDireccionEnvio("Av. Central 123");
    }
}
