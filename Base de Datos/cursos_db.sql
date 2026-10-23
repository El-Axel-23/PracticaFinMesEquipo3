CREATE DATABASE cursos_db;
USE cursos_db;

CREATE TABLE carreras (
  id INT(11) NOT NULL AUTO_INCREMENT,
  carrera VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY nombre (carrera)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE cursos (
  id INT(11) NOT NULL AUTO_INCREMENT,
  carrera_id INT(11) NOT NULL,
  codigo VARCHAR(10) NOT NULL,
  nombre VARCHAR(150) NOT NULL,
  descripcion TEXT DEFAULT NULL,
  url_imagen VARCHAR(150) DEFAULT NULL,
  salon VARCHAR(20) NOT NULL,
  edificio VARCHAR(50) DEFAULT NULL,
  total_horas INT(11) NOT NULL,
  dias_atencion VARCHAR(100) DEFAULT NULL,
  horario VARCHAR(50) DEFAULT NULL,
  cupo_maximo INT(11) NOT NULL DEFAULT 50,
  PRIMARY KEY (id),
  UNIQUE KEY uq_codigo (codigo),
  KEY fk_cursos_carreras (carrera_id),
  CONSTRAINT fk_cursos_carreras FOREIGN KEY (carrera_id)
  REFERENCES carreras (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE alumnos (
  id INT(11) NOT NULL AUTO_INCREMENT,
  matricula VARCHAR(15) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  correo_institucional VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_matricula (matricula),
  UNIQUE KEY uq_correo (correo_institucional)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE inscripciones (
  id INT(11) NOT NULL AUTO_INCREMENT,
  curso_id INT(11) NOT NULL,
  alumno_id INT(11) NOT NULL,
  fecha_registro DATE NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_curso_alumno (curso_id, alumno_id),
  KEY fk_inscripciones_cursos (curso_id),
  KEY fk_inscripciones_alumnos (alumno_id),
  CONSTRAINT fk_inscripciones_cursos FOREIGN KEY (curso_id)
  REFERENCES cursos (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_inscripciones_alumnos FOREIGN KEY (alumno_id)
  REFERENCES alumnos (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO carreras(id,carrera) VALUES
(1,'Ingeniería de Sistemas'),
(2,'Ingeniería Industrial');

INSERT INTO cursos(id,carrera_id,codigo,nombre,descripcion,url_imagen,salon,edificio,total_horas,dias_atencion,horario,cupo_maximo) VALUES
(1,1,'IS102','Programación Avanzada','Curso de programación avanzada con patrones de diseño y arquitectura MVP.','upload/cursos/programacion.png','A-102','Edificio K',80,'Lunes, Miércoles, Viernes','10:00 - 12:00',50),
(2,1,'IS201','Bases de Datos II','Diseño avanzado de bases de datos, consultas y optimización.','upload/cursos/baseDedatos.png','A-102','Edificio K',80,'Martes, Jueves','10:00 - 12:00',50),
(3,1,'IS301','Inteligencia Artificial','Fundamentos de IA y aprendizaje automático.','upload/cursos/inteligenciaArtificial.png','B-201','Edificio D',60,'Lunes, Miércoles','12:00 - 14:00',40),
(4,1,'IS302','Redes de Computadoras','Configuración y administración de redes.','upload/cursos/redes.png','A-102','Edificio B',60,'Martes, Jueves','08:00 - 10:00',50);


INSERT INTO alumnos(id,matricula,nombre,correo_institucional) VALUES
(1,'202113045','Javier Ramirez','j.ramirez@universidad.edu'),
(2,'202113046','Axel Madison Coronado','am.coronado@universidad.edu');

INSERT INTO inscripciones(id,curso_id,alumno_id,fecha_registro) VALUES
(1,1,1,'2024-05-20'),
(2,2,1,'2024-05-20'),
(3,1,2,'2024-05-21'),
(4,3,2,'2024-05-21');

-- Catalogo filtrado por carrera
SELECT c.id, c.nombre, c.salon, c.url_imagen
FROM cursos c
WHERE c.carrera_id = 1;

-- Ver registros
SELECT c.id, c.nombre, c.url_imagen, c.cupo_maximo, COUNT(i.id) AS inscritos
FROM cursos c
LEFT JOIN inscripciones i ON i.curso_id = c.id
WHERE c.carrera_id = 1
GROUP BY c.id;
-- total de inscritos
SELECT COUNT(*) AS total FROM inscripciones;