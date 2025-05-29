package com.ecomarquet_vm.ecomarquet_vm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomarquet_vm.ecomarquet_vm.Model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, String> {
}

