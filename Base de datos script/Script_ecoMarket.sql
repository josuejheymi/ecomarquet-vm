-- 1. Usuario
INSERT INTO usuario (id, nombre, email, contrasena, rol, fechaCreacion, activo) VALUES
(1, 'Ana', 'ana.perez@mail.com', 'anaperez1994', 'cliente', '03-12-2024', true),
(2, 'Luis', 'luis.ramirez@mail.com', 'luisramirez2004', 'Cliente', '12-02-2023', false),
(3, 'Carla', 'carla.lopez@mail.com', 'carlalopez2000', 'Empleado', '01-10-2021', true),
(4, 'Pedro', 'pedro.pavez@mail.com', 'pedropavez1976', 'Gerente', '21-01-2020', false);

-- 2. Reporte
INSERT INTO reporte (id, tipo, fechaGeneracion) VALUES
(1, 'tipo 1' ,'12-02-2023'),
(2, 'tipo 2' ,'03-04-2025'),
(3, 'tipo 1' ,'01-04-2024'),
(4, 'tipo 1' ,'20-05-2025');

-- 3. Carrito Compra
INSERT INTO carritoCompra (carrito_id, producto_id, total) VALUES
(1, 3, 20000.00),
(2, 2, 12500.00),
(3, 1, 8000.00),
(4, 3, 20000.00);

-- 4. Producto
INSERT INTO producto (producto_id, nombre, descripcion, precio, stock, categoria, fechaCreacion ) VALUES
(1, 'Detergente en laminas', 'Descripcion detergente', 18000.00, 10 ,'categoria 1', '01-01-2020'),
(2, 'Champu solido', 'Descripcion Champu', 12500.00, 20,'categoria 1', '01-01-2020'),
(3, 'Bolsa reutilizable', 'Descripcion bolsa reutilizable', 8000.00, 10,'categoria 2', '01-02-2020'),
(4, 'Cepillo de bambu', 'Descripcion cepillo de bambu', 7000.00, 30,'categoria 1', '01-01-2020');

-- 5. Transaccion
INSERT INTO transaccion (id, fecha, total, estado, metodoPago) VALUES
(1, '01-01-2025', 40000.00, 'pagado', 'Debito'),
(2, '01-01-2024', 20000.00, 'pagado', 'Credito'),
(3, '01-01-2023', 25000.00, 'pagado', 'Debito'),
(4, '01-01-2022', 16000.00, 'pagado', 'Debito');

-- 6. Pedido
INSERT INTO pedido (id, fecha, estado, direccionEnvio) VALUES
(1, '02-02-2025','pendiente', 'direccion 1'),
(2, '02-02-2024','enviado', 'direccion 4'),
(3, '02-02-2023' ,'enviado', 'direccion 2'),
(4, '02-02-2022','enviado', 'direccion 3');

-- 7. Factura
INSERT INTO factura (id, fecha, detalles, total) VALUES
(1, '01-01-2025', 'Informacion detalles', 40000.00),
(2, '01-01-2024', 'Informacion detalles', 20000.00),
(3, '01-01-2023', 'Informacion detalles', 25000.00),
(4, '01-01-2022', 'Informacion detalles', 16000.00);

-- 8. Cupon
INSERT INTO cupon (codigo, descuento, validoHasta) VALUES
(01012024, 40, '20-06-2025'),
(01012024, 20, '01-07-2025'),
(01012023, 25, '29-05-2025'),
(01012022, 16, '30-05-2025');

