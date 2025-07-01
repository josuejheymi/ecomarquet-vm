package com.ecomarquet_vm.ecomarquet_vm;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.TransaccionRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.TransaccionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    private Transaccion transaccionEjemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Arrange: crear objeto de prueba
        transaccionEjemplo = new Transaccion();
        transaccionEjemplo.setId("TXN123");
        transaccionEjemplo.setFecha(new Date());
        transaccionEjemplo.setTotal(new BigDecimal("150.75"));
        transaccionEjemplo.setEstado("COMPLETADO");
        transaccionEjemplo.setMetodoPago("Tarjeta");

        // Podrías agregar más setup si quieres
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Transaccion> lista = Arrays.asList(transaccionEjemplo);
        when(transaccionRepository.findAll()).thenReturn(lista);

        // Act
        List<Transaccion> resultado = transaccionService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("TXN123", resultado.get(0).getId());
        verify(transaccionRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Existe() {
        // Arrange
        when(transaccionRepository.findById("TXN123")).thenReturn(Optional.of(transaccionEjemplo));

        // Act
        Optional<Transaccion> resultado = transaccionService.findById("TXN123");

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("COMPLETADO", resultado.get().getEstado());
        verify(transaccionRepository, times(1)).findById("TXN123");
    }

    @Test
    void testFindById_NoExiste() {
        // Arrange
        when(transaccionRepository.findById("NOEXISTE")).thenReturn(Optional.empty());

        // Act
        Optional<Transaccion> resultado = transaccionService.findById("NOEXISTE");

        // Assert
        assertFalse(resultado.isPresent());
        verify(transaccionRepository, times(1)).findById("NOEXISTE");
    }

    @Test
    void testSave() {
        // Arrange
        when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccionEjemplo);

        // Act
        Transaccion resultado = transaccionService.save(transaccionEjemplo);

        // Assert
        assertNotNull(resultado);
        assertEquals("TXN123", resultado.getId());
        verify(transaccionRepository, times(1)).save(transaccionEjemplo);
    }

    @Test
    void testDelete() {
        // Act
        transaccionService.delete("TXN123");

        // Assert
        verify(transaccionRepository, times(1)).deleteById("TXN123");
    }

    @Test
    void testExistsById_True() {
        // Arrange
        when(transaccionRepository.existsById("TXN123")).thenReturn(true);

        // Act
        boolean existe = transaccionService.existsById("TXN123");

        // Assert
        assertTrue(existe);
        verify(transaccionRepository, times(1)).existsById("TXN123");
    }

    @Test
    void testExistsById_False() {
        // Arrange
        when(transaccionRepository.existsById("NOEXISTE")).thenReturn(false);

        // Act
        boolean existe = transaccionService.existsById("NOEXISTE");

        // Assert
        assertFalse(existe);
        verify(transaccionRepository, times(1)).existsById("NOEXISTE");
    }
}
