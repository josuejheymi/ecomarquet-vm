package com.ecomarquet_vm.ecomarquet_vm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
import com.ecomarquet_vm.ecomarquet_vm.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario>findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario findById(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public void delete(String id) {
        usuarioRepository.deleteById(id);
    }
}
