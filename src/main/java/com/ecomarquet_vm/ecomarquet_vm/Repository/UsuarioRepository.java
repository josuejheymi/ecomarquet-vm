package com.ecomarquet_vm.ecomarquet_vm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
    
}

