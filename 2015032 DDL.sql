--Crear Base de Datos
CREATE DATABASE FXColegio;
GO

--Usar Base de Datos Colegio
USE FXColegio;
GO

--Crear Tabla Usuario
CREATE TABLE Usuario(
	idUsuario INTEGER IDENTITY(1,1) NOT NULL,
	activo BIT NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	clave VARCHAR(255) NOT NULL,
	PRIMARY KEY(idUsuario)
);

--Crear Tabla Grado
CREATE TABLE Grado(
	idGrado INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(idGrado) 
);

--Crear Tabla Carrera
CREATE TABLE Carrera(
	idCarrera INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(idCarrera)
);

--Crear Tabla Alumno
CREATE TABLE Alumno(
	idAlumno INTEGER IDENTITY(2016001,1) NOT NULL,
	nombres VARCHAR(255) NOT NULL,
	apellidos VARCHAR(255) NOT NULL,
	fechaNacimiento DATE NOT NULL,
	idGrado INTEGER NOT NULL,
	idCarrera INTEGER NULL,
	jornada VARCHAR(255) NOT NULL,
	telefono INTEGER NULL,
	direccion VARCHAR(255) NULL,
	PRIMARY KEY(idAlumno),
	FOREIGN KEY(idGrado) REFERENCES Grado(idGrado)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idCarrera) REFERENCES Carrera(idCarrera)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

--Crear Tabla Profesor
CREATE TABLE Profesor(
	idProfesor INTEGER IDENTITY(1,1) NOT NULL,
	nombres VARCHAR(255) NOT NULL,
	apellidos VARCHAR(255) NOT NULL,
	fechaNacimiento DATE NOT NULL,
	dpi VARCHAR(255) NOT NULL,
	direccion VARCHAR(255) NOT NULL,
	telefono INTEGER NOT NULL,
	PRIMARY KEY(idProfesor)
);

--Crear Tabla Materia
CREATE TABLE Materia(
	idMateria INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(idMateria)
);

--Crear Tabla Actividad
CREATE TABLE Actividad ( 
	idActividad INTEGER IDENTITY(1,1) NOT NULL,
	tipoActividad VARCHAR(255) NOT NULL,
	nombre VARCHAR(255) NOT NULL, 
	valor INTEGER NOT NULL, 
	PRIMARY KEY(idActividad)
);

--Crear Tabla Bimestre
CREATE TABLE Bimestre (
	idBimestre INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	PRIMARY KEY(idBimestre)
);

--Crear Tabla Seccion Técnica
CREATE TABLE SeccionTecnica(
	idSeccionTecnica INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(idSeccionTecnica)
);

--Crear Tabla Seccion Académica
CREATE TABLE SeccionAcademica(
	idSeccionAcademica INTEGER IDENTITY(1,1) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(idSeccionAcademica),
);

--Crear Tabla Profesor Materia Seccion
CREATE TABLE ProfesorMateriaSeccion (
	idProfesorMateriaSeccion INTEGER IDENTITY(1,1) NOT NULL,
	idProfesor INTEGER NOT NULL,
	idMateria INTEGER NOT NULL,
	idSeccionTecnica INTEGER NULL,
	idSeccionAcademica INTEGER NULL,
	PRIMARY KEY(idProfesorMateriaSeccion),
	FOREIGN KEY(idProfesor) REFERENCES Profesor(idProfesor)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idMateria) REFERENCES Materia(idMateria)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idSeccionTecnica) REFERENCES SeccionTecnica(idSeccionTecnica)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idSeccionAcademica) REFERENCES SeccionAcademica(idSeccionAcademica)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

--Crear Tabla Actividad Materia Bimestre Alumno
CREATE TABLE ActividadMateriaBimestreAlumno (
	idActividadMateriaBimestre INTEGER IDENTITY(1,1) NOT NULL,
	idMateria INTEGER NOT NULL,
	idBimestre INTEGER NOT NULL,
	idActividad INTEGER NOT NULL,
	idAlumno INTEGER NOT NULL,
	valor INTEGER NOT NULL,
	PRIMARY KEY(idActividadMateriaBimestre),
	FOREIGN KEY(idMateria) REFERENCES Materia(idMateria)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idBimestre) REFERENCES Bimestre(idBimestre)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idActividad) REFERENCES Actividad(idActividad)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idAlumno) REFERENCES Alumno(idAlumno)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

--Crear Tabla Secciones Alumno
CREATE TABLE SeccionAlumno(
	idSeccionAlumno INTEGER IDENTITY(1,1) NOT NULL,
	idAlumno INTEGER NOT NULL,
	idSeccionTecnica INTEGER NOT NULL,
	idSeccionAcademica INTEGER NOT NULL,
	PRIMARY KEY(idSeccionAlumno),
	FOREIGN KEY(idAlumno) REFERENCES Alumno(idAlumno)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idSeccionTecnica) 
	REFERENCES SeccionTecnica(idSeccionTecnica)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(idSeccionAcademica) 
	REFERENCES SeccionAcademica(idSeccionAcademica)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);