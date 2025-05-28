package com.ecomarquet_vm.ecomarquet_vm.Repository;

// Importo el modelo CarritoCompra
import com.ecomarquet_vm.ecomarquet_vm.Model.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, String>{

}