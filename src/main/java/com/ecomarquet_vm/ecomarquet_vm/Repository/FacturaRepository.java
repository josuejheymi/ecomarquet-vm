package com.ecomarquet_vm.ecomarquet_vm.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {

}
