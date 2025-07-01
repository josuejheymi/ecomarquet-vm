package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.FacturaRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.FacturaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    private Factura factura;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Transaccion transaccion = new Transaccion();
        transaccion.setId("tx001");
        transaccion.setTotal(new BigDecimal("500.00"));

        factura = new Factura();
        factura.setId("f001");
        factura.setFecha(new Date());
        factura.setDetalles("Factura de prueba");
        factura.setTotal(new BigDecimal("500.00"));
        factura.setTransaccion(transaccion);
    }

    @Test
    public void testGetAllFacturas() {
        // Arrange
        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        // Act
        List<Factura> result = facturaService.getAllFacturas();

        // Assert
        assertEquals(1, result.size());
        assertEquals("f001", result.get(0).getId());
        verify(facturaRepository).findAll();
    }

    @Test
    public void testGetFacturaById_Existe() {
        // Arrange
        when(facturaRepository.findById("f001")).thenReturn(Optional.of(factura));

        // Act
        Optional<Factura> result = facturaService.getFacturaById("f001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Factura de prueba", result.get().getDetalles());
        verify(facturaRepository).findById("f001");
    }

    @Test
    public void testGetFacturaById_NoExiste() {
        // Arrange
        when(facturaRepository.findById("noexiste")).thenReturn(Optional.empty());

        // Act
        Optional<Factura> result = facturaService.getFacturaById("noexiste");

        // Assert
        assertFalse(result.isPresent());
        verify(facturaRepository).findById("noexiste");
    }

    @Test
    public void testSaveFactura() {
        // Arrange
        when(facturaRepository.save(factura)).thenReturn(factura);

        // Act
        Factura result = facturaService.saveFactura(factura);

        // Assert
        assertNotNull(result);
        assertEquals("f001", result.getId());
        verify(facturaRepository).save(factura);
    }

    @Test
    public void testUpdateFactura_Existe() {
        // Arrange
        Factura nuevosDatos = new Factura();
        nuevosDatos.setFecha(new Date());
        nuevosDatos.setDetalles("Actualizada");
        nuevosDatos.setTotal(new BigDecimal("600.00"));
        Transaccion nuevaTransaccion = new Transaccion();
        nuevaTransaccion.setId("tx002");
        nuevosDatos.setTransaccion(nuevaTransaccion);

        when(facturaRepository.findById("f001")).thenReturn(Optional.of(factura));
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        // Act
        Factura result = facturaService.updateFactura("f001", nuevosDatos);

        // Assert
        assertNotNull(result);
        verify(facturaRepository).findById("f001");
        verify(facturaRepository).save(factura);
    }

    @Test
    public void testUpdateFactura_NoExiste() {
        // Arrange
        Factura nuevosDatos = new Factura();
        nuevosDatos.setFecha(new Date());
        nuevosDatos.setDetalles("Nuevo dato");
        nuevosDatos.setTotal(new BigDecimal("700.00"));
        Transaccion nuevaTransaccion = new Transaccion();
        nuevaTransaccion.setId("tx003");
        nuevosDatos.setTransaccion(nuevaTransaccion);

        when(facturaRepository.findById("f999")).thenReturn(Optional.empty());
        when(facturaRepository.save(any(Factura.class))).thenReturn(nuevosDatos);

        // Act
        Factura result = facturaService.updateFactura("f999", nuevosDatos);

        // Assert
        assertNotNull(result);
        assertEquals("f999", result.getId());
        verify(facturaRepository).findById("f999");
        verify(facturaRepository).save(nuevosDatos);
    }

    @Test
    public void testDeleteFactura() {
        // Arrange
        doNothing().when(facturaRepository).deleteById("f001");

        // Act
        facturaService.deleteFactura("f001");

        // Assert
        verify(facturaRepository).deleteById("f001");
    }
}
