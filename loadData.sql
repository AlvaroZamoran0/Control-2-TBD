-- \connect cambia la conexión a la nueva base de datos
\c control2

-- Poblando la tabla Usuario
INSERT INTO Usuario (ID, username, Contrasena, Correo) VALUES
(1, 'Martinex', 'Pass1234!', 'user1@example.com'),
(2, 'Alvaro', 'StrongPass22#', 'user2@example.com'),
(3, 'Braulio', 'MySecureP@sswd', 'user3@example.com'),
(4, 'Cami', 'Password2024*', 'user4@example.com'),
(5, 'Manu', 'SafeP@ss123', 'user5@example.com'),
(6, 'Diego', 'UltraSecure!99', 'user6@example.com');

-- Poblando la tabla Tarea
INSERT INTO Tarea (ID, Nombre, Descripcion, Fechatermino, IDuser, Status) VALUES
(1, 'Comprar materiales', 'Comprar madera y pintura para la casa', '2024-12-01', 1, false),
(2, 'Entregar reporte', 'Finalizar y enviar el informe anual', '2024-11-20', 2, true),
(3, 'Reparar computadora', 'Llevar la computadora al servicio técnico', '2024-11-15', 3, false),
(4, 'Estudiar para examen', 'Revisar los capítulos 4 y 5 del libro', '2024-11-25', 4, false),
(5, 'Reservar hotel', 'Hacer una reserva para el viaje de enero', '2024-11-30', 1, true),
(6, 'Actualizar CV', 'Actualizar el currículum para postular a nuevos trabajos', '2024-11-18', 5, false),
(7, 'Hacer compras', 'Comprar víveres para la semana', '2024-11-19', 1, true),
(8, 'Pagar cuentas', 'Realizar el pago de servicios básicos', '2024-11-22', 2, false),
(9, 'Llamar al doctor', 'Agendar una cita médica de control', '2024-11-24', 3, false),
(10, 'Planificar vacaciones', 'Definir itinerario y actividades para el próximo viaje', '2024-12-05', 4, true);

-- Poblando la tabla Notificaciones
INSERT INTO notificaciones (id, idUser, idTarea, mensaje, leida) VALUES
(1, 3, 3, 'Tarea proxima a vencer', false),
(2, 5, 6, 'Tarea proxima a vencer', false),
(3, 1, 7, 'Tarea proxima a vencer', false),
(4, 2, 8, 'Tarea proxima a vencer', false),
(5, 3, 9, 'Tarea proxima a vencer', false);
