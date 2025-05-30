package com.ecomarquet_vm.ecomarquet_vm.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Factura;
import com.ecomarquet_vm.ecomarquet_vm.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaById(String id) {
        return facturaRepository.findById(id);
    }

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura updateFactura(String id, Factura facturaDetails) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setFecha(facturaDetails.getFecha());
            factura.setDetalles(facturaDetails.getDetalles());
            factura.setTotal(facturaDetails.getTotal());
            return facturaRepository.save(factura);
        }).orElseGet(() -> {
            facturaDetails.setId_Factura(id);
            return facturaRepository.save(facturaDetails);
        });
    }

    public void deleteFactura(String id) {
        facturaRepository.deleteById(id);
    }
}
