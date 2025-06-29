package com.ecomarquet_vm.ecomarquet_vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ecomarquet_vm.ecomarquet_vm.Repository.CarritoCompraRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.CuponRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.FacturaRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.PedidoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ProductoRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.ReporteRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.TransaccionRepository;
import com.ecomarquet_vm.ecomarquet_vm.Repository.UsuarioRepository;



@Profile ("dev")
@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired 
    private ProductoRepository productoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private CuponRepository cuponRepository;
    @Autowired
    private CarritoCompraRepository carritoCompraRepository;
    @Override
 public void run(String... args) throws Exception {
    

 }


}
