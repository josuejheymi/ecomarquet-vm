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
@Autowired
private FacturaRepository FacturaRepository;
public List<Transaccion> findAll() {
return TransaccionRepository.findAll();
}
public Transaccion findById(Long id) {
return TransaccionRepository.findById(id).orElse(null);
}
public Transaccion save(Transaccion Transaccion) {
return TransaccionRepository.save(Transaccion);
}
public void delete(Long id) {
TransaccionRepository.deleteById(id);

public Factura crearFacturaDesdeTransaccion(Long id) {
        FacturaProjection projection = TransaccionRepository.generarFactura(id);

        Factura factura = new Factura();
        factura.setId(UUID.randomUUID().toString());
        factura.setFecha(projection.getFecha());
        factura.setDetalles(projection.getDetalles());
        factura.setTotal(projection.getTotal());

        return facturaRepository.save(factura);
    }
}
}

