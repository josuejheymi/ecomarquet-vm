package com.ecomarquet_vm.ecomarquet_vm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecomarquet_vm.ecomarquet_vm.Repository.CarritoCompraRepository;

@SpringBootTest
class EcomarquetVmApplicationTests {

	@Test
	void contextLoads() {

	}
	@Autowired
	private CarritoCompraRepository carritoCompraRepository;
	@Test
	public void testCarritoCompraRepository() {
		// Aquí puedes agregar pruebas específicas para el CarritoCompraRepository
		// Por ejemplo, verificar si el repositorio se inicializa correctamente
		assert carritoCompraRepository != null : "El CarritoCompraRepository no se ha inicializado correctamente";
	}

}
