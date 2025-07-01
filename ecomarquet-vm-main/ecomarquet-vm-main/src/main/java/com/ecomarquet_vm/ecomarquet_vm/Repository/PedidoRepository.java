package com.ecomarquet_vm.ecomarquet_vm.Repository;

import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    // Buscar pedidos por estado
    @Query("SELECT p FROM Pedido p WHERE p.estado = :estado")
    List<Pedido> buscarPorEstado(@Param("estado") String estado);

    // Buscar pedidos por fecha exacta
    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido = :fecha")
    List<Pedido> buscarPorFechaPedido(@Param("fecha") Date fecha);

    // Buscar pedidos por coincidencia parcial en la dirección de envío
    @Query("SELECT p FROM Pedido p WHERE LOWER(p.direccionEnvio) LIKE LOWER(CONCAT('%', :direccion, '%'))")
    List<Pedido> buscarPorDireccionEnvio(@Param("direccion") String direccion);
}
