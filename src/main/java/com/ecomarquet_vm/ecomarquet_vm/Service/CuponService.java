package com.ecomarquet_vm.ecomarquet_vm.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    // Obtener todos los cupones
    public List<Cupon> findAll() {
        return cuponRepository.findAll();
    }

    // Buscar cupón por ID (cupon_id)
    public Optional<Cupon> findById(String id) {
        return cuponRepository.findById(id);
    }

    // Buscar cupón por código (codigo)
    public Cupon findByCodigo(String codigo) {
        return cuponRepository.findByCodigo(codigo);
    }

    // Guardar o actualizar un cupón
    public Cupon save(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    // Eliminar un cupón por ID
    public void delete(String id) {
        cuponRepository.deleteById(id);
    }

    // Aplicar descuento a una transacción (usa el método del Repository)
    public BigDecimal aplicarDescuento(String transaccionId) {
        return cuponRepository.aplicarDescuento(transaccionId);
    }

    // Método adicional para verificar si un cupón es válido (fecha y descuento)
    public boolean esCuponValido(String codigoCupon) {
        Cupon cupon = cuponRepository.findByCodigo(codigoCupon);
        if (cupon == null) {
            return false;
        }
        return cupon.getValidoHasta().after(new Date()) && 
               cupon.getDescuento().compareTo(BigDecimal.ZERO) > 0;
    }
}