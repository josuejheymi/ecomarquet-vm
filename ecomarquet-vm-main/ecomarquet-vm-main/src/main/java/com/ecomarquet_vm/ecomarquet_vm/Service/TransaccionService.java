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
    private static TransaccionRepository transaccionRepository;
    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public static Optional<Transaccion> findById(String id) { 
        return transaccionRepository.findById(id);
    }

    public static Transaccion save(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public void delete(String id) {
        transaccionRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return transaccionRepository.existsById(id);
    }
}
