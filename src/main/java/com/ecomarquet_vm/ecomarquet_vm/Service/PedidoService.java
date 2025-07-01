    package com.ecomarquet_vm.ecomarquet_vm.Service;

    import com.ecomarquet_vm.ecomarquet_vm.Model.Pedido;
    import com.ecomarquet_vm.ecomarquet_vm.Repository.PedidoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        public List<Pedido> getAll() {
            return pedidoRepository.findAll();
        }

        public Optional<Pedido> obtenerPorId(String id) {
            return pedidoRepository.findById(id);
        }

        public Pedido guardar(Pedido pedido) {
            return pedidoRepository.save(pedido);
        }

        public void eliminarPorId(String id) {
            pedidoRepository.deleteById(id);
        }

        public List<Pedido> buscarPorEstado(String estado) {
            return pedidoRepository.buscarPorEstado(estado);
        }

        public List<Pedido> buscarPorFecha(Date fecha) {
            return pedidoRepository.buscarPorFechaPedido(fecha);
        }

        public List<Pedido> buscarPorDireccion(String direccion) {
            return pedidoRepository.buscarPorDireccionEnvio(direccion);
        }
    }