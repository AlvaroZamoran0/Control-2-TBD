-- Parte 1: Crear la Base de Datos
CREATE DATABASE "control2"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Chile.1252'
    LC_CTYPE = 'Spanish_Chile.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Parte 2: Conectar a la base de datos y crear las tablas
\connect control2;

-- Tabla: Usuario
CREATE TABLE Usuario (
    ID SERIAL PRIMARY KEY, 
    username VARCHAR(25) NOT NULL, 
    Contrasena VARCHAR(255) NOT NULL,
    Correo VARCHAR(255) UNIQUE NOT NULL
);

-- Tabla: Tarea
CREATE TABLE Tarea (
    ID SERIAL PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL CHECK (LENGTH(Descripcion) <= 500),
    fechaTermino DATE NOT NULL, 
    idUser INT NOT NULL, 
    status BOOLEAN NOT NULL DEFAULT FALSE
);

-- Tabla: Notificaciones
CREATE TABLE notificaciones (
    id SERIAL PRIMARY KEY,        
    idUser INTEGER NOT NULL,     
    idTarea BIGINT NOT NULL,     
    mensaje TEXT NOT NULL,        
    leida BOOLEAN NOT NULL        
);