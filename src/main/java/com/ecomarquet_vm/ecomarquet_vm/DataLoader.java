package com.ecomarquet_vm.ecomarquet_vm;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.ecomarquet_vm.ecomarquet_vm.Model.*;
import com.ecomarquet_vm.ecomarquet_vm.Repository.*;
import net.datafaker.Faker;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("es"));

    private final String[] roles = {"ADMIN", "USER", "MODERATOR"};
    private final String[] estadosPedido = {"PENDIENTE", "ENVIADO", "ENTREGADO", "CANCELADO"};
    private final String[] metodosPago = {"TARJETA", "PAYPAL", "TRANSFERENCIA"};
    private final String[] categoriasProducto = {"LIMPIEZA", "ALIMENTACIÓN", "HIGIENE", "REUTILIZABLES"};

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private CarritoCompraRepository carritoRepository;
    @Autowired private TransaccionRepository transaccionRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private FacturaRepository facturaRepository;
    @Autowired private CuponRepository cuponRepository;
    @Autowired private ReporteRepository reporteRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Producto> productos = crearProductos(20);
        List<Usuario> usuarios = crearUsuarios(10);
        asignarProductosACarritos(usuarios, productos);
        crearTransaccionesCompletas(usuarios);
    }

    private List<Producto> crearProductos(int cantidad) {
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Producto p = new Producto();
            p.setId("P" + (i + 1));
            p.setNombre("Eco " + faker.commerce().material());
            p.setDescripcion("Producto ecológico para " + faker.commerce().department());
            p.setPrecio(new BigDecimal(faker.number().randomDouble(2, 1000, 10000)));
            p.setStock(faker.number().numberBetween(10, 100));
            p.setCategoria(faker.options().option(categoriasProducto));
            p.setFechaCreacion(faker.date().past(100, TimeUnit.DAYS));
            productos.add(p);
        }
        return productoRepository.saveAll(productos);
    }
    private List<Usuario> crearUsuarios(int cantidad) {
    List<Usuario> usuarios = new ArrayList<>();
    Set<String> nombresUsados = new HashSet<>();

    // Primero crear usuarios sin carritos
    for (int i = 0; i < cantidad; i++) {
        Usuario usuario = new Usuario();
        usuario.setId("U" + (i + 1));

        String username;
        do {
            username = faker.name().username();
            username = username.length() > 50 ? username.substring(0, 50) : username;
        } while (nombresUsados.contains(username));

        nombresUsados.add(username);
        usuario.setNombre(username);
        usuario.setEmail(username + "@ejemplo.com");
        usuario.setContraseña("123456");
        usuario.setRol(faker.options().option(roles));
        usuario.setActivo("true"); // Usar String "true" en lugar de boolean true
        usuario.setFechaCreacion(new Date());
        
        usuarios.add(usuario);
    }

    // Guardar usuarios primero
    List<Usuario> usuariosGuardados = usuarioRepository.saveAll(usuarios);

    // Luego crear y guardar carritos
    List<CarritoCompra> carritos = new ArrayList<>();
    for (Usuario usuario : usuariosGuardados) {
        CarritoCompra carrito = new CarritoCompra();
        carrito.setId("C" + usuario.getId());
        carrito.setProductos(new ArrayList<>());
        carrito.setTotal(BigDecimal.ZERO);
        carrito.setUsuario(usuario);
        
        carritos.add(carrito);
        usuario.setCarrito(carrito);
    }

    // Guardar carritos
    carritoRepository.saveAll(carritos);
    
    return usuarioRepository.saveAll(usuariosGuardados);
}

    private void asignarProductosACarritos(List<Usuario> usuarios, List<Producto> productos) {
        usuarios.forEach(usuario -> {
            CarritoCompra carrito = usuario.getCarrito();
            List<Producto> productosEnCarrito = faker.collection()
                .minLen(1).maxLen(5)
                .suppliers(() -> faker.options().nextElement(productos))
                .generate();

            carrito.setProductos(productosEnCarrito);

            BigDecimal total = productosEnCarrito.stream()
                .map(Producto::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            carrito.setTotal(total);

            carritoRepository.save(carrito);
        });
    }

    private void crearTransaccionesCompletas(List<Usuario> usuarios) {
        usuarios.forEach(usuario -> {
            if (usuario.getCarrito() == null) {
                throw new IllegalStateException("Usuario sin carrito: " + usuario.getId());
            }

            Transaccion transaccion = new Transaccion();
            transaccion.setId("T" + usuario.getId());
            transaccion.setFecha(faker.date().past(30, TimeUnit.DAYS));
            transaccion.setTotal(usuario.getCarrito().getTotal());
            transaccion.setEstado(faker.options().option("COMPLETADA", "PENDIENTE", "RECHAZADA"));
            transaccion.setMetodoPago(faker.options().option(metodosPago));
            transaccionRepository.save(transaccion);

            Pedido pedido = new Pedido();
            pedido.setId("PD" + usuario.getId());
            pedido.setFechaPedido(transaccion.getFecha());
            pedido.setEstado(faker.options().option(estadosPedido));
            pedido.setDireccionEnvio(faker.address().fullAddress());
            pedido.setUsuario(usuario);
            pedido.setTransaccion(transaccion);
            pedidoRepository.save(pedido);

            Factura factura = new Factura();
            factura.setId("F" + usuario.getId());
            factura.setFecha(transaccion.getFecha());
            factura.setDetalles("Compra de " + usuario.getCarrito().getProductos().size() + " productos ecológicos");
            factura.setTotal(transaccion.getTotal());
            factura.setTransaccion(transaccion);
            facturaRepository.save(factura);

            if (faker.bool().bool()) {
                Cupon cupon = new Cupon();
                cupon.setId("CP" + usuario.getId());
                cupon.setCodigo("ECO-" + faker.lorem().characters(5).toUpperCase());
                cupon.setDescuento(new BigDecimal(faker.number().numberBetween(5, 30)));
                cupon.setValidoHasta(faker.date().future(60, TimeUnit.DAYS));
                cupon.setTransaccion(transaccion);
                cuponRepository.save(cupon);
            }

            Reporte reporte = new Reporte();
            reporte.setId("R" + usuario.getId());
            reporte.setTipo("REPORTE_" + transaccion.getEstado());
            reporte.setFechaGeneracion(new Date());
            reporte.setDatos("Transacción ID: " + transaccion.getId());
            reporte.setTransaccion(transaccion);
            reporteRepository.save(reporte);
        });
    }
}
