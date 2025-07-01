package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;
import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ReporteRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.ReporteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Transaccion transaccion = new Transaccion();
        transaccion.setId("tx001");

        reporte = new Reporte();
        reporte.setId("rep001");
        reporte.setTipo("Ventas");
        reporte.setFechaGeneracion(new Date());
        reporte.setDatos("Resumen del día");
        reporte.setTransaccion(transaccion);
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        // Act
        List<Reporte> result = reporteService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("rep001", result.get(0).getId());
        verify(reporteRepository).findAll();
    }

    @Test
    public void testFindById_Existe() {
        // Arrange
        when(reporteRepository.findById("rep001")).thenReturn(Optional.of(reporte));

        // Act
        Reporte result = reporteService.findById("rep001");

        // Assert
        assertNotNull(result);
        assertEquals("Ventas", result.getTipo());
        verify(reporteRepository).findById("rep001");
    }

    @Test
    public void testFindById_NoExiste() {
        // Arrange
        when(reporteRepository.findById("noexiste")).thenReturn(Optional.empty());

        // Act
        Reporte result = reporteService.findById("noexiste");

        // Assert
        assertNull(result);
        verify(reporteRepository).findById("noexiste");
    }

    @Test
    public void testSave() {
        // Arrange
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        // Act
        Reporte result = reporteService.save(reporte);

        // Assert
        assertNotNull(result);
        assertEquals("rep001", result.getId());
        verify(reporteRepository).save(reporte);
    }

    @Test
    public void testDelete() {
        // Arrange
        doNothing().when(reporteRepository).deleteById("rep001");

        // Act
        reporteService.delete("rep001");

        // Assert
        verify(reporteRepository).deleteById("rep001");
    }
}
//TEST -REPORTE LISTO