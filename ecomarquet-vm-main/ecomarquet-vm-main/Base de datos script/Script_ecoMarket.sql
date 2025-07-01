-- 1. Usuario
INSERT INTO usuario (usuario_id, nombre, email, contrasena, rol, fecha_creacion, activo) VALUES
('1', 'Ana', 'ana.perez@mail.com', 'anaperez1994', 'cliente', '2020-01-01', true),
('2', 'Luis', 'luis.ramirez@mail.com', 'luisramirez2004', 'Cliente', '2020-01-01', false),
('3', 'Carla', 'carla.lopez@mail.com', 'carlalopez2000', 'Empleado', '2020-01-01', true),
('4', 'Pedro', 'pedro.pavez@mail.com', 'pedropavez1976', 'Gerente', '2020-01-01', false);

-- 2. Reporte
INSERT INTO reporte (reporte_id, tipo, fecha_generacion) VALUES
('1', 'tipo 1' ,'12-02-2023'),
('2', 'tipo 2' ,'03-04-2025'),
('3', 'tipo 1' ,'01-04-2024'),
('4', 'tipo 1' ,'20-05-2025');

-- 3. Carrito Compra
INSERT INTO carrito_compra (carrito_id, producto_id, total) VALUES
('1', 3, 20000.00),
('2', 2, 12500.00),
('3', 1, 8000.00),
('4', 3, 20000.00);

-- 4. Producto
INSERT INTO producto (producto_id, nombre, descripcion, precio, stock, categoria, fecha_creacion ) VALUES
('1', 'Detergente en laminas', 'Descripcion detergente', 18000.00, 10 ,'categoria 1', '2020-01-02'),
('2', 'Champu solido', 'Descripcion Champu', 12500.00, 20,'categoria 1', '2020-01-02'),
('3', 'Bolsa reutilizable', 'Descripcion bolsa reutilizable', 8000.00, 10,'categoria 2', '2020-01-02'),
('4', 'Cepillo de bambu', 'Descripcion cepillo de bambu', 7000.00, 30,'categoria 1', '2020-01-01');

-- 5. Transaccion
INSERT INTO transaccion (transaccion_id, fecha, total, estado, metodo_pago) VALUES
('1', '2020-01-01', 40000.00, 'pagado', 'Debito'),
('2', '2020-01-01', 20000.00, 'pagado', 'Credito'),
('3', '2020-01-01', 25000.00, 'pagado', 'Debito'),
('4', '2020-01-01', 16000.00, 'pagado', 'Debito');

-- 6. Pedido
INSERT INTO pedido (pedido_id, fecha, estado, direccion_envio) VALUES
('1', '2020-01-01', 'pendiente', 'direccion 1'),
('2', '2020-01-01', 'enviado', 'direccion 4'),
('3', '2020-01-01', 'enviado', 'direccion 2'),
('4', '2020-01-01', 'enviado', 'direccion 3');

-- 7. Factura
INSERT INTO factura (factura_id, fecha, detalles, total) VALUES
('1', '01-01-2025', 'Informacion detalles', 40000.00),
('2', '01-01-2024', 'Informacion detalles', 20000.00),
('3', '01-01-2023', 'Informacion detalles', 25000.00),
('4', '01-01-2022', 'Informacion detalles', 16000.00);

-- 8. Cupon
INSERT INTO cupon (codigo, descuento, valido_hasta) VALUES
('01012024', 40, '2020-01-01'),
('01012024', 20, '2020-01-01'),
('01012023', 25, '2020-01-01'),
('01012022', 16, '2020-01-01');

