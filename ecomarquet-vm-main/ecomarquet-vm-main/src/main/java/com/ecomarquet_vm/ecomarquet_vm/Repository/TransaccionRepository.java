package com.ecomarquet_vm.ecomarquet_vm.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, String>{
}
