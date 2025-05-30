package com.ecomarquet_vm.ecomarquet_vm.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository TransaccionRepository;
    
    public List<Transaccion> findAll() {
        return TransaccionRepository.findAll();
    }
    
    public Transaccion findById(String id) {
        return TransaccionRepository.findById(id).orElse(null);
    }
    
    public Transaccion save(Transaccion Transaccion) {
        return TransaccionRepository.save(Transaccion);
    }
    
    public void delete(String id) {
        TransaccionRepository.deleteById(id);
    }
}

