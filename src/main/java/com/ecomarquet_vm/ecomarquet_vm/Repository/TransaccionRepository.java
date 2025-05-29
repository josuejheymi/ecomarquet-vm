package com.ecomarquet_vm.ecomarquet_vm.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{
    @Query("""
        SELECT 
            CAST(t.id AS string) AS id,
            t.fecha AS fecha,
            CONCAT('Factura de transacción ', t.id, ' con método ', t.metodoPago) AS detalles,
            t.total AS total
        FROM Transaccion t
        WHERE t.id = :transaccionId
    """)
    FacturaProjection generarFactura(@Param("id") Long id);

}
