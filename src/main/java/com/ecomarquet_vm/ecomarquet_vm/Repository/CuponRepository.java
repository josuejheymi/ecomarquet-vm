package com.ecomarquet_vm.ecomarquet_vm.Repository;



import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;



import java.math.BigDecimal;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;





public interface CuponRepository extends JpaRepository<Cupon, String> {



  /**

   * Busca un cupón por su código.

   *

   * @param codigo el código del cupón

   * @return el cupón encontrado o null si no existe

   */

  Cupon findByCodigo(String codigo);



@Query("""

  SELECT t.total - (t.total * COALESCE(SUM(c.descuento), 0) / 100) 

  FROM Cupon c 

  JOIN c.transaccion t 

  WHERE t.id = :transaccionId""")

BigDecimal aplicarDescuento(@Param("id") String id);

}