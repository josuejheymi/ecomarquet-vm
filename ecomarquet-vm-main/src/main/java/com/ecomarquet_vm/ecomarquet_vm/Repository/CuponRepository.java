package com.ecomarquet_vm.ecomarquet_vm.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuponRepository extends JpaRepository<Cupon, Long>{

@Query("""
    SELECT t.total - (t.total * COALESCE(SUM(c.descuento), 0) / 100) 
    FROM Cupon c 
    JOIN c.transaccion t 
    WHERE t.id = :transaccionId""")
BigDecimal aplicarDescuento(@Param("id") Long id);
}
