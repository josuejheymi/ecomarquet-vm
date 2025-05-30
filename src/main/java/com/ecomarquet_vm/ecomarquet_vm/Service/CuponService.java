package com.ecomarquet_vm.ecomarquet_vm.Service;

import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CuponService {
@Autowired
private CuponRepository CuponRepository;
public List<Cupon> findAll() {
return CuponRepository.findAll();
}
public Cupon findById(String codigo) {
return CuponRepository.findById(codigo).orElse(null);
}
public Cupon save(Cupon Cupon) {
return CuponRepository.save(Cupon);
}
public void delete(String codigo) {
CuponRepository.deleteById(codigo);
}
public Cupon aplicarDescuento(String id){
return CuponRepository.findByCodigo(id);
}
}