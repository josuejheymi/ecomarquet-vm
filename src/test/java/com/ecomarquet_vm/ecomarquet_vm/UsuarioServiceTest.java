package com.ecomarquet_vm.ecomarquet_vm;

import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
import com.ecomarquet_vm.ecomarquet_vm.Repository.UsuarioRepository;
import com.ecomarquet_vm.ecomarquet_vm.Service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId("u123");
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@example.com");
        usuario.setContraseña("password123");
        usuario.setRol("cliente");
        usuario.setFechaCreacion(new Date());
        usuario.setActivo("si");
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Usuario> listaUsuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        // Act
        List<Usuario> result = usuarioService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getNombre());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_Existe() {
        // Arrange
        when(usuarioRepository.findById("u123")).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = usuarioService.findById("u123");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("juan@example.com", result.get().getEmail());
        verify(usuarioRepository, times(1)).findById("u123");
    }

    @Test
    public void testFindById_NoExiste() {
        // Arrange
        when(usuarioRepository.findById("noexiste")).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = usuarioService.findById("noexiste");

        // Assert
        assertFalse(result.isPresent());
        verify(usuarioRepository, times(1)).findById("noexiste");
    }

    @Test
    public void testSave() {
        // Arrange
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario result = usuarioService.save(usuario);

        // Assert
        assertNotNull(result);
        assertEquals("u123", result.getId());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testDelete() {
        // Arrange
        doNothing().when(usuarioRepository).deleteById("u123");

        // Act
        usuarioService.delete("u123");

        // Assert
        verify(usuarioRepository, times(1)).deleteById("u123");
    }
}
