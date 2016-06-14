USE FXColegio;

INSERT INTO Usuario(activo, nombre, clave) VALUES(0, 'abc', 'abc');

INSERT INTO Bimestre(nombre) VALUES('I Bimestre');
INSERT INTO Bimestre(nombre) VALUES('II Bimestre');
INSERT INTO Bimestre(nombre) VALUES('III Bimestre');
INSERT INTO Bimestre(nombre) VALUES('IV Bimestre');
INSERT INTO Bimestre(nombre) VALUES('V Bimestre');

INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('No Aplica', 'N/A');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA4AM', 'Bachillerato 4to A');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA5AM', 'Bachillerato 5to A');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA6AM', 'Bachillerato 6to A');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA4BM', 'Bachillerato 4to B');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA5BM', 'Bachillerato 5to B');
INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('BA6BM', 'Bachillerato 6to B');

INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('No Aplica', 'N/A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('IN4AM', 'Informática 4to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('IN5AM', 'Informática 5to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('IN6AM', 'Informática 6to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('MB4AM', 'Mecánica 4to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('MA5AM', 'Mecánica 5to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('MA5AM', 'Mecánica 6to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('EL4AM', 'Electricidad 4to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('EL5AM', 'Electricidad 5to. A');
INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('EL6AM', 'Electricidad 6to. A');


INSERT INTO Grado(nombre, descripcion) VALUES('4to Diversificado', 'Nivel Medio');
INSERT INTO Grado(nombre, descripcion) VALUES('5to Diversificado', 'Nivel Medio');
INSERT INTO Grado(nombre, descripcion) VALUES('6to Diversificado', 'Nivel Medio');

INSERT INTO Carrera(nombre, descripcion) VALUES('Informática', 'Carrera Técnica');
INSERT INTO Carrera(nombre, descripcion) VALUES('Mecánica', 'Carrera Técnica');
INSERT INTO Carrera(nombre, descripcion) VALUES('Electronica', 'Carrera Técnica');
INSERT INTO Carrera(nombre, descripcion) VALUES('Dibujo', 'Carrera Técnica');
INSERT INTO Carrera(nombre, descripcion) VALUES('Electricidad', 'Carrera Técnica');

INSERT INTO Materia(nombre, descripcion) VALUES('Matemáticas', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Física', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Quimica', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Sociales', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Literatura Universal', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Literatura Hispanoamericana', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Ingles', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Biología', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Filosofía', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Conferencias', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Seminario', 'Clases Academicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Taller', 'Clases Tecnicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Teoría', 'Clases Tecnicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Cálculo', 'Clases Tecnicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Dibujo', 'Clases Tecnicas');
INSERT INTO Materia(nombre, descripcion) VALUES('Religion', 'Clases Tecnicas');

INSERT INTO Alumno(nombres, apellidos, fechaNacimiento, idGrado, idCarrera, jornada, telefono, direccion) 
VALUES('José Rafael', 'Morente González', '02/10/1998', 2, 1, 'Matutina', 48777048, 'Zona 14');

INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Claudio Danilo', 'Canel', '01/06/2016', '471648464645646', 23614682, 'Zona 7');
INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Darwin', 'Coronado', '01/06/2016', '151616121216543', 23265464, 'Zona 7');
INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Carlos', 'Cabrera', '01/06/2016', '166846155646446', 48165184, 'Zona 7');
INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Favio', 'García', '01/06/2016', '156464812156454', 48615481, 'Zona 7');
INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Oscar', 'Bernard', '01/06/2016', '214651264121645', 54844641, 'Zona 7');
INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) 
VALUES('Juan Pablo', 'Elel', '01/06/2016', '154846548457165', 58711066, 'Zona 7');