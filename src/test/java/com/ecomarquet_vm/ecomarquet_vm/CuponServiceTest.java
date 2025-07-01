package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CuponRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.CuponService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    private Cupon cupon;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Transaccion transaccion = new Transaccion();
        transaccion.setId("tx123");
        transaccion.setTotal(new BigDecimal("1000.00"));

        cupon = new Cupon();
        cupon.setId("c123");
        cupon.setCodigo("DESCUENTO10");
        cupon.setDescuento(new BigDecimal("10.0"));
        cupon.setValidoHasta(new Date(System.currentTimeMillis() + 86400000)); // válido mañana
        cupon.setTransaccion(transaccion);
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(cuponRepository.findAll()).thenReturn(List.of(cupon));

        // Act
        List<Cupon> result = cuponService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("DESCUENTO10", result.get(0).getCodigo());
        verify(cuponRepository).findAll();
    }

    @Test
    public void testFindById_Existe() {
        // Arrange
        when(cuponRepository.findById("c123")).thenReturn(Optional.of(cupon));

        // Act
        Optional<Cupon> result = cuponService.findById("c123");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("DESCUENTO10", result.get().getCodigo());
        verify(cuponRepository).findById("c123");
    }

    @Test
    public void testFindById_NoExiste() {
        // Arrange
        when(cuponRepository.findById("noexiste")).thenReturn(Optional.empty());

        // Act
        Optional<Cupon> result = cuponService.findById("noexiste");

        // Assert
        assertFalse(result.isPresent());
        verify(cuponRepository).findById("noexiste");
    }

    @Test
    public void testFindByCodigo() {
        // Arrange
        when(cuponRepository.findByCodigo("DESCUENTO10")).thenReturn(cupon);

        // Act
        Cupon result = cuponService.findByCodigo("DESCUENTO10");

        // Assert
        assertNotNull(result);
        assertEquals("c123", result.getId());
        verify(cuponRepository).findByCodigo("DESCUENTO10");
    }

    @Test
    public void testSave() {
        // Arrange
        when(cuponRepository.save(cupon)).thenReturn(cupon);

        // Act
        Cupon result = cuponService.save(cupon);

        // Assert
        assertNotNull(result);
        assertEquals("c123", result.getId());
        verify(cuponRepository).save(cupon);
    }

    @Test
    public void testDelete() {
        // Arrange
        doNothing().when(cuponRepository).deleteById("c123");

        // Act
        cuponService.delete("c123");

        // Assert
        verify(cuponRepository).deleteById("c123");
    }

    @Test
    public void testAplicarDescuento() {
        // Arrange
        when(cuponRepository.aplicarDescuento("tx123")).thenReturn(new BigDecimal("900.00"));

        // Act
        BigDecimal result = cuponService.aplicarDescuento("tx123");

        // Assert
        assertEquals(new BigDecimal("900.00"), result);
        verify(cuponRepository).aplicarDescuento("tx123");
    }

    @Test
    public void testEsCuponValido_Valido() {
        // Arrange
        when(cuponRepository.findByCodigo("DESCUENTO10")).thenReturn(cupon);

        // Act
        boolean esValido = cuponService.esCuponValido("DESCUENTO10");

        // Assert
        assertTrue(esValido);
        verify(cuponRepository).findByCodigo("DESCUENTO10");
    }

    @Test
    public void testEsCuponValido_NoExiste() {
        // Arrange
        when(cuponRepository.findByCodigo("INVALIDO")).thenReturn(null);

        // Act
        boolean esValido = cuponService.esCuponValido("INVALIDO");

        // Assert
        assertFalse(esValido);
        verify(cuponRepository).findByCodigo("INVALIDO");
    }

    @Test
    public void testEsCuponValido_FechaExpirada() {
        // Arrange
        cupon.setValidoHasta(new Date(System.currentTimeMillis() - 86400000)); // ayer
        when(cuponRepository.findByCodigo("DESCUENTO10")).thenReturn(cupon);

        // Act
        boolean esValido = cuponService.esCuponValido("DESCUENTO10");

        // Assert
        assertFalse(esValido);
    }

    @Test
    public void testEsCuponValido_DescuentoCero() {
        // Arrange
        cupon.setDescuento(BigDecimal.ZERO);
        when(cuponRepository.findByCodigo("DESCUENTO10")).thenReturn(cupon);

        // Act
        boolean esValido = cuponService.esCuponValido("DESCUENTO10");

        // Assert
        assertFalse(esValido);
    }
}
