package com.ecomarquet_vm.ecomarquet_vm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, String> {
    // Custom query methods can be defined here if needed
    // For example, you could add methods to find reports by type or date
}