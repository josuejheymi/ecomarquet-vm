package com.ecomarquet_vm.ecomarquet_vm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Reporte;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ReporteRepository;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }
    public Reporte findById(String id) {
        return reporteRepository.findById(id).orElse(null);
    }
    public  Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }
    public void delete(String id) {
        reporteRepository.deleteById(id);
    }
     public boolean existsById(String id) {
        return reporteRepository.existsById(id); // Añade este método
    }
}
