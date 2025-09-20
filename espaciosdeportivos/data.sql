-- 25 registros para PERSONA

INSERT INTO persona (
    fecha_nacimiento,
    nombre,
    a_paterno,
    a_materno,
    telefono,
    email,
    ci
) VALUES 
('1990-05-12', 'Carlos', 'Gonzales', 'Lopez', '7894561', 'carlos.gonzales@gmail.com', '1234567'),
('1985-11-23', 'María', 'Fernandez', 'Rojas', '7654321', 'maria.fernandez@gmail.com', '9876543'),
('2000-02-08', 'Luis', 'Ramirez', 'Torrez', '7123456', 'luis.ramirez@gmail.com', '4567890'),
('1995-07-30', 'Ana', 'Sanchez', 'Perez', '7981234', 'ana.sanchez@gmail.com', '3216549'),
('1988-03-15', 'Jorge', 'Mendoza', 'Quispe', '7012345', 'jorge.mendoza@gmail.com', '6543210'),
('1992-06-18', 'Sofia', 'Vargas', 'Luna', '7345678', 'sofia.vargas@gmail.com', '1122334'),
('1999-09-09', 'Diego', 'Castro', 'Morales', '7456123', 'diego.castro@gmail.com', '7788990'),
('1987-12-01', 'Valeria', 'Ortiz', 'Flores', '7567890', 'valeria.ortiz@gmail.com', '9988776'),
('1993-04-25', 'Andrés', 'Rojas', 'Gutiérrez', '7678901', 'andres.rojas@gmail.com', '3344556'),
('1996-08-14', 'Camila', 'Torres', 'Salazar', '7789012', 'camila.torres@gmail.com', '6677889'),
('1991-01-10', 'Fernando', 'López', 'Cruz', '7234567', 'fernando.lopez@gmail.com', '4455667'),
('1989-10-05', 'Isabel', 'Martínez', 'Delgado', '7345123', 'isabel.martinez@gmail.com', '5566778'),
('1997-03-22', 'Ricardo', 'Paredes', 'Aguilar', '7456789', 'ricardo.paredes@gmail.com', '6677880'),
('1994-06-30', 'Lucía', 'Gómez', 'Rivera', '7561234', 'lucia.gomez@gmail.com', '7788991'),
('1998-12-12', 'Esteban', 'Navarro', 'Silva', '7672345', 'esteban.navarro@gmail.com', '8899002'),
('1990-04-17', 'Paola', 'Cortez', 'Mamani', '7123987', 'paola.cortez@gmail.com', '1122445'),
('1986-07-08', 'Martín', 'Zeballos', 'Choque', '7234890', 'martin.zeballos@gmail.com', '2233445'),
('1992-11-19', 'Natalia', 'Camacho', 'Vargas', '7345671', 'natalia.camacho@gmail.com', '3344557'),
('1999-05-03', 'Sebastián', 'Peña', 'Rojas', '7456129', 'sebastian.pena@gmail.com', '4455668'),
('1993-09-27', 'Gabriela', 'Flores', 'Guzmán', '7567891', 'gabriela.flores@gmail.com', '5566779'),
('1996-02-14', 'Rodrigo', 'Salinas', 'Delgado', '7678902', 'rodrigo.salinas@gmail.com', '6677881'),
('1988-08-08', 'Carla', 'Mamani', 'Quispe', '7789013', 'carla.mamani@gmail.com', '7788992'),
('1995-01-01', 'Daniel', 'Gutiérrez', 'Rivera', '7890123', 'daniel.gutierrez@gmail.com', '8899003'),
('1991-06-06', 'Verónica', 'Luna', 'Aguilar', '7012346', 'veronica.luna@gmail.com', '9900112'),
('1997-10-10', 'Pablo', 'Cárdenas', 'Torrez', '7123457', 'pablo.cardenas@gmail.com', '1011121');


-- cliente
INSERT INTO cliente (
    id_cliente,
    estado_cliente
) VALUES 
(1, 'activo'),
(2, 'activo'),
(3, 'activo'),
(4, 'activo'),
(5, 'activo'),
(6, 'activo');



-- usuario_control

INSERT INTO usuario_control (
    id_us_control,
    estado_operativo,
    hora_inicio_turno,
    hora_fin_turno,
    direccion
) VALUES 
(12, 'activo', '08:00:00', '16:00:00', 'Av. Sucre 101, La Paz'),
(13, 'activo', '14:00:00', '22:00:00', 'Calle Comercio 55, La Paz'),
(14, 'activo', '07:00:00', '15:00:00', 'Zona Central, Calle 3, La Paz'),
(15, 'activo', '10:00:00', '18:00:00', 'Av. Camacho 200, La Paz'),
(16, 'activo', '06:00:00', '14:00:00', 'Calle Murillo 88, La Paz');


-- administrador
INSERT INTO administrador (
    id_administrador,
    cargo,
    direccion
) VALUES 
(7, 'Administrador', 'Av. Libertador 123, La Paz'),
(8, 'Administrador', 'Calle 21 de Calacoto, La Paz'),
(9, 'Administrador', 'Zona Sur, Calle 10, La Paz'),
(10, 'Administrador', 'Av. Arce 456, La Paz'),
(11, 'Administrador', 'Calle Jaimes Freyre 89, La Paz');


-- invitado
INSERT INTO invitado (
    id_invitado,
    verificado
) VALUES 
(17, true),
(18, true),
(19, true),
(20, true),
(21, true),
(22, true),
(23, true),
(24, true),
(25, true);


-- cancha


