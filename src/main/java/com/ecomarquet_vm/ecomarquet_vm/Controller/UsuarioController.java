package com.ecomarquet_vm.ecomarquet_vm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
import com.ecomarquet_vm.ecomarquet_vm.Service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }
    @GetMapping("/{id}")    //Busca al usuario meidante el ID
    public Usuario getById(@PathVariable String id) {
        return usuarioService.findById(id);
    }
    @PostMapping    
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
    @PutMapping("/{id}")    //Actualiza datos del usuario con ID
    public Usuario update(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId_usuario(id); // Ensure the ID is set for the update
        return usuarioService.save(usuario);
    }
    @DeleteMapping("/{id}") // Elimina usuario por el ID
    public void delete(@PathVariable String id) {
        usuarioService.delete(id);
    }
    
    

    

}
