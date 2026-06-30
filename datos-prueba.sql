CREATE DATABASE gestion_tareas_db;
USE gestion_tareas_db;

CREATE TABLE tarea (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, 
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT NULL,
    estado ENUM('PENDIENTE', 'EN_PROGRESO', 'COMPLETADA', 'CANCELADA') NOT NULL DEFAULT 'PENDIENTE',
    prioridad ENUM('BAJA', 'MEDIA', 'ALTA', 'URGENTE') NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    fechaActualizacion DATETIME NOT NULL
);

INSERT INTO tarea (titulo, descripcion, estado, prioridad, fechaCreacion, fechaActualizacion) 
VALUES 
('Hacer el mercado', 'Comprar frutas, verduras, leche y arroz para la semana.', 'COMPLETADA', 'BAJA', '2026-06-25 08:00:00', '2026-06-25 09:30:00'),
('Estudiar para el examen', 'Repasar los conceptos de bases de datos y Spring Boot.', 'COMPLETADA', 'ALTA', '2026-06-26 14:00:00', '2026-06-26 17:00:00'),
('Lavar la ropa', 'Lavar la ropa oscura y colgarla antes de que llueva.', 'EN_PROGRESO', 'MEDIA', '2026-06-28 09:00:00', '2026-06-28 09:15:00'),
('Pagar el internet', 'Entrar a la aplicación del banco y pagar la factura antes del vencimiento.', 'PENDIENTE', 'URGENTE', '2026-06-28 15:00:00', '2026-06-28 15:00:00'),
('Arreglar la habitación', 'Organizar el escritorio, barrer y trapear el piso.', 'PENDIENTE', 'BAJA', '2026-06-28 15:10:00', '2026-06-28 15:10:00');

select * from tarea;

-- love p