package com.ecomarquet_vm.ecomarquet_vm.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository; // Corregido: minúscula

    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> findById(String id) { // Usamos Optional para mejor manejo de nulls
        return transaccionRepository.findById(id);
    }

    public Transaccion save(Transaccion transaccion) { // Corregido: parámetro en minúscula
        // Aquí podrías añadir lógica de validación si es necesario
        return transaccionRepository.save(transaccion);
    }

    public void delete(String id) {
        transaccionRepository.deleteById(id);
    }

    // Método adicional sugerido: Verificar si una transacción existe
    public boolean existsById(String id) {
        return transaccionRepository.existsById(id);
    }

    // Método adicional sugerido: Aplicar lógica de negocio específica
    /*
    public Transaccion procesarTransaccion(Transaccion transaccion) {
        // Lógica adicional de procesamiento aquí
        return transaccionRepository.save(transaccion);
    }
    */
}
