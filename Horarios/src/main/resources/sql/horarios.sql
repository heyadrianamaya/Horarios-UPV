-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 12-11-2019 a las 02:15:37
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `horarios`
--

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `addAula`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAula` (IN `p_id` VARCHAR(10), IN `p_nombre` VARCHAR(100), IN `p_tipo` VARCHAR(20), IN `p_cap` INT(11))  begin 
    insert into aulas(id, nombre, tipo, capacidad) values (p_id, p_nombre, p_tipo,p_cap);
end$$

DROP PROCEDURE IF EXISTS `addAulaEquipo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAulaEquipo` (IN `p_id_equipo` INT(11), IN `p_id_aula` VARCHAR(10), IN `p_cantidad` INT(11))  begin
    insert into aula_equipo(id_equipo, id_aula, cantidad) VALUES (p_id_equipo, p_id_aula, p_cantidad);
end$$

DROP PROCEDURE IF EXISTS `addCarrera`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCarrera` (IN `p_id` INT, IN `p_nombre` VARCHAR(100))  begin
    insert into carrera(idcarrera, nombre_carrera) VALUES (p_id,p_nombre);
end$$

DROP PROCEDURE IF EXISTS `addCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCategoria` (IN `p_nombre` VARCHAR(100), IN `p_descripcion` VARCHAR(300))  begin 
    insert into categorias_equipo(nombre, descripcion) values (p_nombre,p_descripcion);
end$$

DROP PROCEDURE IF EXISTS `addEquipo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addEquipo` (IN `p_nombre` VARCHAR(100), IN `p_descripcion` VARCHAR(300), IN `p_categoria` INT(11))  begin
    insert into equipo(nombre, descripcion, id_categoria) values (p_nombre, p_descripcion, p_categoria);
end$$

DROP PROCEDURE IF EXISTS `addGrupo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addGrupo` (IN `p_clv_grupo` VARCHAR(40), IN `p_cuatrimestre` INT, IN `p_clv_plan` VARCHAR(10))  begin 
    insert into grupos(clv_grupo, cuatrimestre, clv_plan) values(p_clv_grupo, p_cuatrimestre,p_clv_plan); 
end$$

DROP PROCEDURE IF EXISTS `addPlan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addPlan` (IN `p_clave` VARCHAR(10), IN `p_nombre` VARCHAR(45), IN `p_nivel` VARCHAR(3), IN `p_id_carrera` INT)  begin
    insert into plan_estudios(clv_plan, nombre_plan, nivel, idcarrera) VALUES (p_clave, p_nombre, p_nivel, p_id_carrera);
end$$

DROP PROCEDURE IF EXISTS `addUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `p_clave_usuario` VARCHAR(50), IN `p_password` VARCHAR(41), IN `p_tipo_usuario` CHAR(4), IN `p_id_carrera` INT, IN `p_nombre_usuario` VARCHAR(50), IN `p_nivel_ads` VARCHAR(5), IN `p_contrato` VARCHAR(3), IN `p_telefono` CHAR(10), IN `p_imr` INT)  begin
    insert into usuarios(clv_usuario, pass_usuario, idcarrera, nombre_usuario, telefono, IMR, contrato, tipo_usuario, nivel_ads)
    VALUES (p_clave_usuario, p_password, p_id_carrera, p_nombre_usuario, p_telefono, p_imr, p_contrato, p_tipo_usuario, p_nivel_ads);
end$$

DROP PROCEDURE IF EXISTS `deleteUserProfesor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUserProfesor` (IN `p_clave` VARCHAR(50))  begin
    delete from usuarios_prof where clv_usuario = p_clave;
end$$

DROP PROCEDURE IF EXISTS `getAulas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAulas` ()  begin
    select * from aulas;
end$$

DROP PROCEDURE IF EXISTS `getCarreras`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCarreras` ()  begin 
    select * from carrera;
end$$

DROP PROCEDURE IF EXISTS `getCategorias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCategorias` ()  begin 
    select * from categorias_equipo;
end$$

DROP PROCEDURE IF EXISTS `getDisponibilidad`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDisponibilidad` (IN `p_clave_usuario` VARCHAR(50), IN `p_horario_matutino` INT)  BEGIN
    if p_horario_matutino = 1 then
        select * from disponibilidad where clv_usuario = p_clave_usuario and disponibilidad.espacio_tiempo<=8 order by dia, espacio_tiempo;
    else
        select * from disponibilidad where clv_usuario = p_clave_usuario and disponibilidad.espacio_tiempo>=8 order by dia, espacio_tiempo;
    end if ;
end$$

DROP PROCEDURE IF EXISTS `getEquipos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEquipos` ()  begin
    select * from equipo;
end$$

DROP PROCEDURE IF EXISTS `getGrupos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrupos` (IN `p_clave_plan` VARCHAR(10))  begin 
    select * from grupos where clv_plan = p_clave_plan;
end$$

DROP PROCEDURE IF EXISTS `getGruposWithIdCarrera`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGruposWithIdCarrera` (IN `p_id_carrera` INT)  begin
    SELECT grupos.clv_grupo, grupos.cuatrimestre, grupos.clv_plan from grupos inner join plan_estudios on grupos.clv_plan = plan_estudios.clv_plan inner join carrera on plan_estudios.idcarrera = carrera.idcarrera where carrera.idcarrera = p_id_carrera;
end$$

DROP PROCEDURE IF EXISTS `getMaterias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMaterias` (IN `p_plan` VARCHAR(10))  begin
    select * from materias where clv_plan = p_plan;
end$$

DROP PROCEDURE IF EXISTS `getPlanes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPlanes` ()  begin 
    select * from plan_estudios;
end$$

DROP PROCEDURE IF EXISTS `getPlanesWithIdCarrera`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPlanesWithIdCarrera` (IN `p_id_carrera` INT)  begin
    select * from plan_estudios where idcarrera = p_id_carrera;
end$$

DROP PROCEDURE IF EXISTS `getUsersProfesor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsersProfesor` (IN `p_idcarrera` INT)  begin
    select * from usuarios_prof where idcarrera = p_idcarrera;
end$$

DROP PROCEDURE IF EXISTS `logearse`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `logearse` (IN `p_usuario` VARCHAR(50), IN `p_contrasena` CHAR(41))  begin
    select * from getUsuarios u where u.clv_usuario = p_usuario and u.pass_usuario= p_contrasena;
end$$

DROP PROCEDURE IF EXISTS `updateAula`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAula` (IN `p_id` VARCHAR(10), IN `p_nombre` VARCHAR(100), IN `p_tipo` VARCHAR(20), IN `p_capacidad` INT(11))  BEGIN
    update aulas set nombre = p_nombre, tipo = p_tipo, capacidad = p_capacidad where id = p_id;
end$$

DROP PROCEDURE IF EXISTS `updateAulaEquipo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAulaEquipo` (IN `p_id_equipo` INT(11), IN `p_id_aula` VARCHAR(10), IN `p_cantidad` INT(11))  begin
    update aula_equipo set cantidad = p_cantidad where id_equipo = p_id_equipo and id_aula =p_id_aula;
end$$

DROP PROCEDURE IF EXISTS `updateCarrera`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCarrera` (IN `p_id` INT, IN `p_nombre` VARCHAR(100))  begin 
    update carrera set nombre_carrera = p_nombre where idcarrera = p_id;
end$$

DROP PROCEDURE IF EXISTS `updatePlan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePlan` (IN `p_clave` VARCHAR(10), IN `p_nombre` VARCHAR(45), IN `p_nivel` VARCHAR(3), IN `p_id_carrera` INT)  begin 
    update plan_estudios set nombre_plan = p_nombre, nivel = p_nivel, idcarrera = p_id_carrera where clv_plan = p_clave;
end$$

DROP PROCEDURE IF EXISTS `updateUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `p_clave_usuario` VARCHAR(50), IN `p_password` VARCHAR(41), IN `p_tipo_usuario` CHAR(4), IN `p_id_carrera` INT, IN `p_nombre_usuario` VARCHAR(50), IN `p_nivel_ads` VARCHAR(5), IN `p_contrato` VARCHAR(3), IN `p_telefono` CHAR(10), IN `p_imr` INT)  begin 
    update usuarios set clv_usuario = p_clave_usuario, 
                        pass_usuario = p_password,
                        tipo_usuario = p_tipo_usuario,
                        idcarrera = p_id_carrera,
                        nombre_usuario = p_nombre_usuario,
                        nivel_ads = p_nivel_ads,
                        contrato = p_contrato,
                        telefono = p_telefono,
                        IMR = p_imr
    where clv_usuario = p_clave_usuario;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aulas`
--

DROP TABLE IF EXISTS `aulas`;
CREATE TABLE IF NOT EXISTS `aulas` (
  `id` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `aulas`
--

INSERT INTO `aulas` (`id`, `nombre`, `tipo`, `capacidad`) VALUES
('A100', 'Aula Ejemplo', 'Lab', 10),
('A20', 'Salon', 'Aula', 20),
('AL', 'A20', 'Aula', 20),
('I116', 'Salón I116', 'Aula', 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aula_equipo`
--

DROP TABLE IF EXISTS `aula_equipo`;
CREATE TABLE IF NOT EXISTS `aula_equipo` (
  `id_equipo` int(11) NOT NULL,
  `id_aula` varchar(10) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id_equipo`,`id_aula`),
  KEY `id_aula` (`id_aula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `aula_equipo`
--

INSERT INTO `aula_equipo` (`id_equipo`, `id_aula`, `cantidad`) VALUES
(2, 'AL', 30),
(2, 'I116', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

DROP TABLE IF EXISTS `carrera`;
CREATE TABLE IF NOT EXISTS `carrera` (
  `idcarrera` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombre_carrera` varchar(100) NOT NULL,
  PRIMARY KEY (`idcarrera`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`idcarrera`, `nombre_carrera`) VALUES
(1, 'Ingeniería en Tecnologías de la Información'),
(2, 'Ingeniería en Mecatrónica'),
(3, 'Ingeniería en Sistemas Automotrices'),
(4, 'Ingeniería en Manufactura'),
(5, 'Licenciatura en Administración y Gestión de PyMEs');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_equipo`
--

DROP TABLE IF EXISTS `categorias_equipo`;
CREATE TABLE IF NOT EXISTS `categorias_equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categorias_equipo`
--

INSERT INTO `categorias_equipo` (`id`, `nombre`, `descripcion`) VALUES
(4, 'Aula ', 'Salón de clase '),
(5, 'ASDAS', 'ASDAS'),
(6, 'Categoria 1', 'Ninguna'),
(7, 'Categoria 1', 'Ninguna'),
(8, 'Categoria 1', 'Ninguna');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disponibilidad`
--

DROP TABLE IF EXISTS `disponibilidad`;
CREATE TABLE IF NOT EXISTS `disponibilidad` (
  `dia` tinyint(4) NOT NULL DEFAULT '1',
  `espacio_tiempo` tinyint(4) NOT NULL,
  `clv_usuario` varchar(50) NOT NULL,
  KEY `usuarios_disponibilidad_FK` (`clv_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `disponibilidad`
--

INSERT INTO `disponibilidad` (`dia`, `espacio_tiempo`, `clv_usuario`) VALUES
(5, 1, 'spolancom@upv.edu.mx'),
(6, 1, 'spolancom@upv.edu.mx'),
(6, 2, 'spolancom@upv.edu.mx'),
(4, 1, 'spolancom@upv.edu.mx'),
(4, 3, 'spolancom@upv.edu.mx'),
(4, 2, 'spolancom@upv.edu.mx'),
(5, 2, 'spolancom@upv.edu.mx'),
(5, 3, 'spolancom@upv.edu.mx'),
(6, 3, 'spolancom@upv.edu.mx'),
(4, 4, 'spolancom@upv.edu.mx'),
(5, 4, 'spolancom@upv.edu.mx'),
(6, 4, 'spolancom@upv.edu.mx'),
(3, 1, 'spolancom@upv.edu.mx'),
(3, 2, 'spolancom@upv.edu.mx'),
(1, 9, 'spolancom@upv.edu.mx'),
(6, 11, 'spolancom@upv.edu.mx'),
(4, 11, 'spolancom@upv.edu.mx'),
(3, 12, 'spolancom@upv.edu.mx'),
(1, 12, 'spolancom@upv.edu.mx'),
(1, 9, 'corozcog@upv.edu.mx'),
(1, 1, 'haviles@upv.edu.mx'),
(1, 2, 'haviles@upv.edu.mx'),
(2, 2, 'haviles@upv.edu.mx'),
(2, 1, 'haviles@upv.edu.mx'),
(3, 1, 'haviles@upv.edu.mx'),
(3, 2, 'haviles@upv.edu.mx'),
(4, 2, 'haviles@upv.edu.mx'),
(4, 1, 'haviles@upv.edu.mx'),
(5, 1, 'haviles@upv.edu.mx'),
(5, 2, 'haviles@upv.edu.mx'),
(6, 2, 'haviles@upv.edu.mx'),
(6, 1, 'haviles@upv.edu.mx'),
(1, 3, 'haviles@upv.edu.mx'),
(2, 3, 'haviles@upv.edu.mx'),
(3, 3, 'haviles@upv.edu.mx'),
(4, 3, 'haviles@upv.edu.mx'),
(5, 3, 'haviles@upv.edu.mx'),
(6, 3, 'haviles@upv.edu.mx'),
(3, 11, 'spolancom@upv.edu.mx'),
(2, 8, 'spolancom@upv.edu.mx'),
(3, 8, 'spolancom@upv.edu.mx'),
(4, 8, 'spolancom@upv.edu.mx'),
(5, 8, 'spolancom@upv.edu.mx'),
(5, 9, 'spolancom@upv.edu.mx'),
(3, 9, 'spolancom@upv.edu.mx'),
(2, 9, 'spolancom@upv.edu.mx'),
(2, 10, 'spolancom@upv.edu.mx'),
(2, 11, 'spolancom@upv.edu.mx'),
(3, 10, 'spolancom@upv.edu.mx'),
(4, 10, 'spolancom@upv.edu.mx'),
(4, 9, 'spolancom@upv.edu.mx'),
(4, 13, 'spolancom@upv.edu.mx'),
(2, 13, 'spolancom@upv.edu.mx'),
(3, 3, 'corozcog@upv.edu.mx'),
(4, 2, 'corozcog@upv.edu.mx'),
(5, 3, 'corozcog@upv.edu.mx'),
(6, 2, 'corozcog@upv.edu.mx'),
(6, 3, 'corozcog@upv.edu.mx'),
(5, 2, 'corozcog@upv.edu.mx'),
(4, 3, 'corozcog@upv.edu.mx'),
(4, 1, 'corozcog@upv.edu.mx'),
(5, 1, 'corozcog@upv.edu.mx'),
(6, 1, 'corozcog@upv.edu.mx'),
(6, 4, 'corozcog@upv.edu.mx'),
(4, 4, 'corozcog@upv.edu.mx'),
(3, 4, 'corozcog@upv.edu.mx'),
(4, 5, 'corozcog@upv.edu.mx'),
(5, 5, 'corozcog@upv.edu.mx'),
(5, 4, 'corozcog@upv.edu.mx'),
(6, 5, 'corozcog@upv.edu.mx'),
(6, 6, 'corozcog@upv.edu.mx'),
(5, 6, 'corozcog@upv.edu.mx'),
(3, 6, 'corozcog@upv.edu.mx'),
(2, 6, 'corozcog@upv.edu.mx'),
(2, 7, 'corozcog@upv.edu.mx'),
(3, 7, 'corozcog@upv.edu.mx'),
(4, 7, 'corozcog@upv.edu.mx'),
(4, 6, 'corozcog@upv.edu.mx'),
(5, 7, 'corozcog@upv.edu.mx'),
(6, 7, 'corozcog@upv.edu.mx'),
(1, 1, 'corozcog@upv.edu.mx'),
(2, 4, 'corozcog@upv.edu.mx'),
(2, 2, 'corozcog@upv.edu.mx'),
(2, 5, 'corozcog@upv.edu.mx');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE IF NOT EXISTS `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `nombre`, `descripcion`, `id_categoria`) VALUES
(2, 'Salon', 'Salon', 4),
(3, 'NOSE', 'AL', 4),
(4, 'NOSE', 'AL', 4),
(5, 'NOSE', 'AL', 4);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `getusuarios`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `getusuarios`;
CREATE TABLE IF NOT EXISTS `getusuarios` (
`clv_usuario` varchar(50)
,`pass_usuario` char(41)
,`tipo_usuario` char(4)
,`idcarrera` tinyint(4)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

DROP TABLE IF EXISTS `grupos`;
CREATE TABLE IF NOT EXISTS `grupos` (
  `clv_grupo` varchar(40) NOT NULL,
  `cuatrimestre` tinyint(10) NOT NULL,
  `clv_plan` varchar(10) NOT NULL,
  PRIMARY KEY (`clv_grupo`),
  KEY `grupos` (`clv_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`clv_grupo`, `cuatrimestre`, `clv_plan`) VALUES
('A100', 3, 'ITI-2010'),
('A200', 6, 'ITI-2010'),
('IM-200', 1, 'IM-2018'),
('ITI-1', 5, 'ITI-2018'),
('SDFSDFSD', 4, 'ITI-2010');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo_asignacion`
--

DROP TABLE IF EXISTS `grupo_asignacion`;
CREATE TABLE IF NOT EXISTS `grupo_asignacion` (
  `clv_grupo` varchar(10) NOT NULL,
  `clv_materia` varchar(10) NOT NULL,
  `dia` varchar(10) NOT NULL,
  `hora` int(10) NOT NULL,
  `nombre_profesor` varchar(40) NOT NULL,
  KEY `NombreFK` (`clv_grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupo_asignacion`
--

INSERT INTO `grupo_asignacion` (`clv_grupo`, `clv_materia`, `dia`, `hora`, `nombre_profesor`) VALUES
('A100', 'ASO-1', '5', 1, 'Said Polanco'),
('A100', 'ASO-1', '4', 1, 'Said Polanco'),
('A100', 'ASO-1', '5', 2, 'Said Polanco'),
('A100', 'ASO-1', '6', 1, 'Said Polanco'),
('A100', 'ASO-1', '6', 2, 'Said Polanco'),
('A100', 'CDI-1', '3', 1, 'Hector Aviles'),
('A100', 'CDI-1', '3', 2, 'Hector Aviles'),
('A200', 'ADS-1', '3', 3, 'Carlos Orozco García'),
('A200', 'ADS-1', '3', 2, 'Carlos Orozco García'),
('A200', 'ADOO-1', '4', 3, 'Said Polanco'),
('A200', 'ADOO-1', '4', 4, 'Said Polanco'),
('A200', 'EP-1', '4', 1, 'Hector Aviles'),
('A200', 'EP-1', '4', 2, 'Hector Aviles'),
('A100', 'CDI-1', '2', 1, 'Hector Aviles'),
('A100', 'CDI-1', '1', 1, 'Hector Aviles'),
('A100', 'CDI-1', '1', 2, 'Hector Aviles'),
('A100', 'CDI-1', '2', 2, 'Hector Aviles'),
('A100', 'ASO-1', '4', 2, 'Said Polanco'),
('A100', 'SW-1', '2', 7, 'Carlos Orozco García'),
('A100', 'SW-1', '3', 5, 'Carlos Orozco García'),
('A100', 'SW-1', '3', 4, 'Carlos Orozco García'),
('A100', 'SW-1', '4', 3, 'Carlos Orozco García'),
('A100', 'SW-1', '4', 4, 'Carlos Orozco García'),
('A100', 'SW-1', '5', 3, 'Carlos Orozco García'),
('SDFSDFSD', 'E-1', '3', 1, 'Said Polanco'),
('SDFSDFSD', 'E-1', '3', 2, 'Said Polanco'),
('SDFSDFSD', 'E-1', '5', 3, 'Said Polanco'),
('SDFSDFSD', 'E-1', '5', 4, 'Said Polanco'),
('SDFSDFSD', 'E-1', '2', 8, 'Said Polanco');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

DROP TABLE IF EXISTS `materias`;
CREATE TABLE IF NOT EXISTS `materias` (
  `clv_materia` varchar(10) NOT NULL,
  `nombre_materia` varchar(80) DEFAULT NULL,
  `creditos` tinyint(4) DEFAULT NULL,
  `cuatrimestre` tinyint(4) NOT NULL,
  `posicion` tinyint(4) NOT NULL,
  `clv_plan` varchar(10) NOT NULL,
  `tipo_materia` char(3) NOT NULL DEFAULT 'ESP',
  `num_horas` int(11) DEFAULT '0',
  PRIMARY KEY (`clv_plan`,`clv_materia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`clv_materia`, `nombre_materia`, `creditos`, `cuatrimestre`, `posicion`, `clv_plan`, `tipo_materia`, `num_horas`) VALUES
('ADM-1', 'ADMINISTRACON DE MANTENIMIENTO', 1, 3, 6, 'IM-2018', 'TRC', 5),
('ADPR-1', 'ADMINISTRACION DE PROYECTOS DE INGENIERIA', 1, 7, 6, 'IM-2018', 'TRC', 3),
('AIND-1', 'AUTOMATIZACION INDUSTRIAL', 1, 6, 7, 'IM-2018', 'TRC', 6),
('AL-1', 'ALGEBRA LINEAL', 1, 1, 3, 'IM-2018', 'TRC', 7),
('AYPDDS-1', 'ADQUISICION Y PROCESAMIENTO DIGITAL DE SEÑALES', 1, 8, 7, 'IM-2018', 'TRC', 5),
('CAV-1', 'CONTROL AVANZADO', 1, 9, 3, 'IM-2018', 'TRC', 7),
('CDIF-1', 'CALCULO DIFERENCIAL', 1, 2, 3, 'IM-2018', 'TRC', 4),
('CDM-1', 'CINEMATICA DE MECANISMOS', 1, 5, 5, 'IM-2018', 'TRC', 4),
('CDME-1', 'CONTROL DE MOTORES ELECTRICOS', 1, 6, 8, 'IM-2018', 'TRC', 4),
('CDROB-1', 'CONTROL DE ROBOTS', 1, 9, 5, 'IM-2018', 'TRC', 8),
('CEYE-1', 'CIRCUITOS ELECTRICOS Y ELECTRONICOS', 1, 3, 7, 'IM-2018', 'TRC', 5),
('CINT-1', 'CALCULO INTEGRAL ', 1, 3, 3, 'IM-2018', 'TRC', 5),
('CIROB-1', 'CINEMATICA DE ROBOTS', 1, 7, 5, 'IM-2018', 'TRC', 6),
('CLP-1', 'CONTROLADORES LOGICOS PROGRAMABLES', 1, 5, 8, 'IM-2018', 'TRC', 6),
('DDROB-1', 'DINAMICA DE ROBOTS', 1, 8, 5, 'IM-2018', 'TRC', 5),
('DDSM-1', 'DISEÑO DE SISTEMAS MECATRONICOS', 1, 8, 2, 'IM-2018', 'TRC', 5),
('DHV-1', 'DESARROLLO HUMANO Y VALORES', 1, 1, 2, 'IM-2018', 'TRC', 3),
('DPIN-1', 'DIBUJO PARA INGENIERIA', 1, 2, 8, 'IM-2018', 'TRC', 6),
('DYSEM-1', 'DISEÑO Y SELECCION DE ELEMENTOS MECANICOS', 1, 7, 4, 'IM-2018', 'TRC', 6),
('E-1', 'ESTANCIA I', 1, 4, 8, 'IM-2018', 'TRC', 8),
('E-2', 'ESTANCIA II', 1, 7, 7, 'IM-2018', 'TRC', 3),
('EOYE-1', 'EXPRESION ORAL Y ESCRITA I', 1, 1, 8, 'IM-2018', 'TRC', 5),
('EOYE-2', 'EXPRESION ORAL Y ESCRITA II', 1, 9, 6, 'IM-2018', 'TRC', 5),
('EPR-1', 'ETICA PROFESIONAL', 1, 4, 2, 'IM-2018', 'TRC', 3),
('EYMAG-1', 'ELECTRICIDAD Y MAGNETISMO', 1, 2, 5, 'IM-2018', 'TRC', 6),
('EYPDM-1', 'ESTRUCTURA Y PROPIEDADES DE LOS MATERIALES', 1, 4, 4, 'IM-2018', 'TRC', 3),
('FIS-1', 'FISICA', 1, 2, 4, 'IM-2018', 'TRC', 6),
('FMAT-1', 'FUNCIONES MATEMATICAS', 1, 1, 5, 'IM-2018', 'TRC', 5),
('FPIN-1', 'FISICA PARA INGENIERIA', 1, 5, 4, 'IM-2018', 'TRC', 4),
('HCOM-1', 'HERRAMIENTAS COMPUTACIONALES', 1, 4, 3, 'IM-2018', 'TRC', 3),
('HCYC-1', 'HABILIDADES COGNITIVAS Y CREATIVIDAD', 1, 3, 2, 'IM-2018', 'TRC', 3),
('HGER-1', 'HABILIDADES GERENCIALES', 1, 5, 2, 'IM-2018', 'TRC', 3),
('I-1', 'INGLES I', 1, 1, 1, 'IM-2018', 'TRC', 6),
('I-2', 'INGLES II', 1, 2, 1, 'IM-2018', 'TRC', 6),
('I-3', 'INGLES III', 1, 3, 1, 'IM-2018', 'TRC', 6),
('I-4', 'INGLES IV', 1, 4, 1, 'IM-2018', 'TRC', 6),
('I-5', 'INGLES V', 1, 5, 1, 'IM-2018', 'TRC', 6),
('I-6', 'INGLES VI', 1, 6, 1, 'IM-2018', 'TRC', 6),
('I-7', 'INGLES VII', 1, 7, 1, 'IM-2018', 'TRC', 6),
('I-8', 'INGLES VIII', 1, 8, 1, 'IM-2018', 'TRC', 6),
('I-9', 'INGLES IX', 1, 9, 1, 'IM-2018', 'TRC', 6),
('IAPCOM-1', 'INGENIERIA ASISTIDA POR COMPUTADORA', 1, 8, 4, 'IM-2018', 'TRC', 5),
('IDCON-1', 'INGENIERIA DE CONTROL', 1, 8, 3, 'IM-2018', 'TRC', 5),
('IEYMC-1', 'INTELIGENCIA EMOCIONAL Y MANEJO DE CONFLICTOS', 1, 2, 2, 'IM-2018', 'TRC', 3),
('IIMYR-1', 'INTRODUCCION A LA INGENIERIA MECATRONICA Y ROBOTICA', 1, 1, 7, 'IM-2018', 'TRC', 4),
('ISMYR-1', 'INTEGRACION DE SISTEMAS MECATRONICOS Y ROBOTICOS', 1, 9, 2, 'IM-2018', 'TRC', 5),
('LDEAD-1', 'LIDERAZGO DE EQUIPOS DE ALTO DESEMPEÑO', 1, 6, 2, 'IM-2018', 'TRC', 3),
('MDCR-1', 'MECANICA DE CUERPO RIGIDO', 1, 3, 5, 'IM-2018', 'TRC', 5),
('MDSMYR-1', 'MANTENIMIENTO DE SISTMAS MECATRONICOS Y ROBOTICOS', 1, 2, 6, 'IM-2018', 'TRC', 5),
('MET-1', 'METROLOGIA', 1, 1, 6, 'IM-2018', 'TRC', 5),
('MPI-1', 'MATEMATICAS PARA INGENIERIA I', 1, 5, 3, 'IM-2018', 'TRC', 4),
('MPI-2', 'MATEMATICAS PARA INGENIERIA II', 1, 6, 3, 'IM-2018', 'TRC', 5),
('MYSDS-1', 'MODELADO Y SIMULACION DE SISTEMAS', 1, 7, 3, 'IM-2018', 'TRC', 5),
('PDMAN-1', ' PROCESOS DE MANUFACTURA', 1, 2, 7, 'IM-2018', 'TRC', 5),
('PDPER-1', 'PROGRAMACION DE PERIFERICOS', 1, 6, 6, 'IM-2018', 'TRC', 6),
('PDRI-1', 'PROGRAMACION DE ROBOTS INDUSTRIALES', 1, 6, 5, 'IM-2018', 'TRC', 6),
('PDSE-1', 'PROGRAMACION DE SISTEMAS EMBEBIDOS', 1, 8, 8, 'IM-2018', 'TRC', 5),
('PEST-1', 'PROGRAMACION ESTRUCTURADA', 1, 5, 6, 'IM-2018', 'TRC', 5),
('PYES-1', 'PROBABILIDAD Y ESTADISTICA', 1, 3, 4, 'IM-2018', 'TRC', 5),
('QM-1', 'QUIMICA BASICA', 1, 1, 4, 'IM-2018', 'TRC', 5),
('RDMAT-1', 'RESISTENCIA DE MATERIALES', 1, 6, 4, 'IM-2018', 'TRC', 6),
('SADM-1', 'SISTEMAS AVANZADOS DE MANUFACTURA', 1, 9, 4, 'IM-2018', 'TRC', 8),
('SDIG-1', 'SISTEMAS DIGITALES', 1, 4, 5, 'IM-2018', 'TRC', 3),
('SDPI-1', 'SISTEMAS DE PRODUCCION INDUSTRIAL', 1, 9, 7, 'IM-2018', 'TRC', 5),
('SDVA-1', 'SISTEMAS DE VISION ARTIFICIAL', 1, 8, 6, 'IM-2018', 'TRC', 5),
('SEDI-1', 'SISTEMAS ELECTRONICOS DE INTERFAZ', 1, 4, 6, 'IM-2018', 'TRC', 7),
('SNEH-1', 'SISTEMAS NEUMATICOS E HIDRAULICOS', 1, 5, 7, 'IM-2018', 'TRC', 5),
('SYAC-1', 'SENSORES Y ACTUADORES', 1, 4, 7, 'IM-2018', 'TRC', 7),
('SYMA-1', 'SEGURIDAD Y MEDIO AMBIENTE', 1, 3, 8, 'IM-2018', 'TRC', 4),
('TERM-1', 'TERMODINAMICA', 1, 7, 2, 'IM-2018', 'TRC', 3),
('A-1', 'ALGORITMOS ', 1, 1, 3, 'ITI-2010', 'TRC', 7),
('AC-1', 'ARQUITECTURA DE COMPUTADORAS', 1, 9, 5, 'ITI-2010', 'TRC', 6),
('ACC-1', 'ADMINISTRACION DE CENTROS DE COMPUTO', 1, 9, 2, 'ITI-2010', 'TRC', 4),
('ADOO-1', 'ANALISIS Y DISEÑO ORIENTADO A OBJETOS', 1, 6, 3, 'ITI-2010', 'TRC', 7),
('ADS-1', 'ANALISIS Y DISEÑO DE SISTEMAS', 1, 6, 6, 'ITI-2010', 'TRC', 6),
('AE-1', 'ANALISIS Y ESTADISTICA', 1, 5, 6, 'ITI-2010', 'TRC', 6),
('AL-1', 'ALGEBRA LINEAL', 1, 3, 6, 'ITI-2010', 'TRC', 4),
('ASI-1', 'ADMINISTRACION DE SISTEMAS INTEGRALES', 1, 8, 7, 'ITI-2010', 'TRC', 4),
('ASO-1', 'ADMINISTRACION DE SISTEMAS OPERATIVOS', 1, 3, 4, 'ITI-2010', 'TRC', 6),
('CDI-1', 'CALCULO DIFERENCIAL E INTEGRAL ', 1, 3, 7, 'ITI-2010', 'TRC', 7),
('CDM-1', 'COMPUTO EN DISPOSITIVOS MOVILES', 1, 8, 5, 'ITI-2010', 'TRC', 6),
('DE-1', 'DESARROLLO DE EMPRENDEDORES', 1, 9, 6, 'ITI-2010', 'TRC', 5),
('DI-1', 'DESARROLLO INTRAPERSONAL', 1, 3, 2, 'ITI-2010', 'TRC', 3),
('DIN-1', 'DISEÑO DE INTERFACES', 1, 7, 2, 'ITI-2010', 'TRC', 5),
('E-1', 'ESTANCIA I', 1, 4, 7, 'ITI-2010', 'TRC', 0),
('E-2', 'ESTANCIA II', 1, 7, 7, 'ITI-2010', 'TRC', 0),
('ED-1', 'ESTRUCTURA DE DATOS', 1, 5, 3, 'ITI-2010', 'TRC', 6),
('EP-1', 'ETICA PROFESIONAL', 1, 6, 2, 'ITI-2010', 'TRC', 3),
('FF-1', 'FUNDAMENTOS DE FISICA', 1, 1, 6, 'ITI-2010', 'TRC', 7),
('FR-1', 'FUNDAMENTOS DE REDES', 1, 1, 5, 'ITI-2010', 'TRC', 4),
('FSI-1', 'FUNDAMENTOS DE SISTEMAS DE INFORMACION', 1, 2, 6, 'ITI-2010', 'TRC', 7),
('GCA-1', 'GRAFICACION POR COMPUTADORA AVANZADA', 1, 7, 5, 'ITI-2010', 'TRC', 6),
('HM-1', 'HERRAMIENTAS MULTIMEDIA', 1, 2, 4, 'ITI-2010', 'TRC', 5),
('HO-1', 'HABILIDADES ORGANIZCIONALES', 1, 5, 2, 'ITI-2010', 'TRC', 3),
('HOFI-1', 'HERRAMIENTAS OFIMATICAS', 1, 1, 4, 'ITI-2010', 'TRC', 5),
('HP-1', 'HABILIDADES DEL PENSAMIENTO', 1, 4, 2, 'ITI-2010', 'TRC', 3),
('I-1', 'INGLES I', 1, 1, 1, 'ITI-2010', 'TRC', 5),
('I-2', 'INGLES II', 1, 2, 1, 'ITI-2010', 'TRC', 5),
('I-3', 'INGLES III', 1, 3, 1, 'ITI-2010', 'TRC', 5),
('I-4', 'INGLES IV', 1, 4, 1, 'ITI-2010', 'TRC', 5),
('I-5', 'INGLES V', 1, 5, 1, 'ITI-2010', 'TRC', 5),
('I-6', 'INGLES VI', 1, 6, 1, 'ITI-2010', 'TRC', 5),
('I-7', 'INGLES VII', 1, 7, 1, 'ITI-2010', 'TRC', 5),
('I-8', 'INGLES VIII', 1, 8, 1, 'ITI-2010', 'TRC', 5),
('I-9', 'INGLES IX', 1, 9, 1, 'ITI-2010', 'TRC', 5),
('IBD-1', 'INTRODUCCION A LAS BASES DE DATOS', 1, 4, 4, 'ITI-2010', 'TRC', 5),
('IE-1', 'INTELIGENCIA EMOCIONAL', 1, 2, 2, 'ITI-2010', 'TRC', 3),
('IGC-1', 'INTRODUCCION A LA GRAFICACION POR COMPUTADORA', 1, 6, 5, 'ITI-2010', 'TRC', 4),
('IMBD-1', 'IMPLEMENTACION DE BASES DE DATOS', 1, 5, 4, 'ITI-2010', 'TRC', 6),
('IN-1', 'INTELIGENCIA DE NEGOCIOS', 1, 8, 4, 'ITI-2010', 'TRC', 7),
('INTI-1', 'INTRODUCCION A LA ITI', 1, 9, 4, 'ITI-2010', 'TRC', 6),
('IPOO-1', 'INTRODUCCION A LA PROGRAMACION ORIENTADA A OBJETO', 1, 4, 3, 'ITI-2010', 'TRC', 6),
('IR-1', 'INGNIERIA EN REQUERIMIENTOS', 1, 5, 7, 'ITI-2010', 'TRC', 6),
('IS-1', 'INGENIERIA DE SOFTWARE', 1, 6, 7, 'ITI-2010', 'TRC', 6),
('ITI-2', 'INTEGRACION DE LAS TECNOLOGIAS DE LA INFORMACION', 1, 9, 7, 'ITI-2010', 'TRC', 6),
('LC-1', 'LOGICA COMPUTACIONAL', 1, 2, 3, 'ITI-2010', 'TRC', 6),
('MB-1', 'MATEMATICAS BASICAS ', 1, 1, 7, 'ITI-2010', 'TRC', 7),
('MD-1', 'MATEMATICAS DISCRETAS', 1, 2, 7, 'ITI-2010', 'TRC', 5),
('MDA-1', 'MINERIA DE DATOS APLICADA', 1, 7, 4, 'ITI-2010', 'TRC', 5),
('NE-1', 'NEGOCIOS ELECTRONICOS', 1, 8, 2, 'ITI-2010', 'TRC', 4),
('PA-1', 'PROCESO ADMINISTRATIVO', 1, 5, 5, 'ITI-2010', 'TRC', 6),
('PE-1', 'PROGRAMACION ESTRUCTURADA ', 1, 3, 3, 'ITI-2010', 'TRC', 6),
('POO-1', 'PROGRAMACION ORIENTADA A OBJETOS', 1, 7, 3, 'ITI-2010', 'TRC', 6),
('PRES-1', 'PROBABILIDAD Y ESTADISTICA ', 1, 4, 6, 'ITI-2010', 'TRC', 6),
('PTI-1', 'PROYECTOS DE TECNOLOGIAS DE LA INFORMACION', 1, 8, 6, 'ITI-2010', 'TRC', 4),
('PW-1', 'PROGRAMACION WEB', 1, 8, 3, 'ITI-2010', 'TRC', 7),
('RTO-1', 'RUTEO', 1, 2, 5, 'ITI-2010', 'TRC', 6),
('SEGI-1', 'SEGURIDAD INFORMATICA', 1, 6, 4, 'ITI-2010', 'TRC', 6),
('SI-1', 'SEMINARIO DE INVESTIGACION', 1, 7, 6, 'ITI-2010', 'TRC', 4),
('SW-1', 'SWITCHEO Y WIRELESS', 1, 3, 5, 'ITI-2010', 'TRC', 6),
('TAW-1', 'TECNOLOGIAS Y APLICACIONES WEB', 1, 9, 3, 'ITI-2010', 'TRC', 6),
('TW-1', 'TECNOLOGIAS WAN', 1, 4, 5, 'ITI-2010', 'TRC', 6),
('VDS-1', 'VALORES DEL SER', 1, 1, 2, 'ITI-2010', 'TRC', 3),
('ADM-ES', 'ADMINISTRACION', 1, 6, 6, 'ITM-2010', 'TRC', 6),
('ADP-ES', 'ADMINISTRACION DE PROYECTOS', 1, 9, 5, 'ITM-2010', 'TRC', 5),
('ALL-CV', 'ALGEBRA LINEAL', 1, 1, 3, 'ITM-2010', 'TRC', 6),
('AUT-ES', 'AUTOMATIZACION', 1, 8, 3, 'ITM-2010', 'TRC', 5),
('CAL-CV', 'CALIDAD', 1, 3, 6, 'ITM-2010', 'TRC', 5),
('CAV-CV', 'CALCULO VECTORIAL', 1, 2, 4, 'ITM-2010', 'TRC', 6),
('CCP-ES', 'CONTABILIDADES Y COSTOS DE PRODUCCION', 1, 8, 2, 'ITM-2010', 'TRC', 5),
('CDI-CV', 'CALCULO DIFERENCIAL E INTEGRAL', 1, 1, 4, 'ITM-2010', 'TRC', 8),
('CIM-CV', 'CIENCIA E INGENIERIA DE LOS MATERIALES', 1, 3, 3, 'ITM-2010', 'TRC', 7),
('DEI-TR', 'DESARROLLO INTRAPERSONAL', 1, 3, 2, 'ITM-2010', 'TRC', 3),
('DII-CV', 'DIBUJO PARA INGENIERIA', 1, 2, 5, 'ITM-2010', 'TRC', 5),
('DME-ES', 'DISEÑO PARA MANUFACTURA Y ENSAMBLE', 1, 9, 2, 'ITM-2010', 'TRC', 5),
('E-1', 'ESTANCIA I', 1, 4, 7, 'ITM-2010', 'TRC', 4),
('E-2', 'ESTANCIA II', 1, 7, 7, 'ITM-2010', 'TRC', 5),
('ECD-CV', 'ECUACIONES DIFERENCIALES', 1, 3, 5, 'ITM-2010', 'TRC', 7),
('ETP-TR', 'ÉTICA PROFESIONAL', 1, 6, 2, 'ITM-2010', 'TRC', 3),
('FEL-CV', 'FUNDAMENTOS DE ELECTRONICA', 1, 6, 3, 'ITM-2010', 'TRC', 6),
('FEP-ES', 'FORMULACION Y EVALUACION DE PROYECTOS', 1, 8, 5, 'ITM-2010', 'TRC', 6),
('FUE-CV', 'FUNDAMENTOS DE ELECTRICIDAD', 1, 5, 3, 'ITM-2010', 'TRC', 5),
('FUQ-CV', 'FUNDAMENTOS DE QUIMICA', 1, 2, 3, 'ITM-2010', 'TRC', 6),
('GEC-ES', 'GESTION DE LA CALIDAD', 1, 5, 6, 'ITM-2010', 'TRC', 7),
('GEM-ES', 'GESTION DE MANTENIMIENTO', 1, 6, 7, 'ITM-2010', 'TRC', 6),
('HAO-TR', 'HABILIDADES ORGANIZACIONALES', 1, 5, 2, 'ITM-2010', 'TRC', 3),
('HAP-TR', 'HABILIDADES DEL PENSAMIENTO', 1, 4, 2, 'ITM-2010', 'TRC', 3),
('HEM-ES', 'HERRAMIENTAS DE MEJORA', 1, 4, 6, 'ITM-2010', 'TRC', 8),
('HEO-TR', 'HERRAMIENTAS OFIMATICAS', 1, 1, 7, 'ITM-2010', 'TRC', 5),
('IIM-ES', 'INTRODUCCION DE LA INGENIERIA EN MANUFACTURA', 1, 1, 5, 'ITM-2010', 'TRC', 5),
('INE-TR', 'INTELIGENCIA EMOCIONAL', 1, 2, 2, 'ITM-2010', 'TRC', 3),
('INGI-TR', 'INGLÉS I', 1, 1, 1, 'ITM-2010', 'TRC', 5),
('INGII-TR  ', 'INGLÉS II', 1, 2, 1, 'ITM-2010', 'TRC', 5),
('INGIII-TR', 'INGLÉS III', 1, 3, 1, 'ITM-2010', 'TRC', 5),
('INGIV-TR', 'INGLÉS IV', 1, 4, 1, 'ITM-2010', 'TRC', 5),
('INGIX-TR', 'INGLÉS IX', 1, 9, 1, 'ITM-2010', 'TRC', 5),
('INGV-TR', 'INGLÉS V', 1, 5, 1, 'ITM-2010', 'TRC', 5),
('INGVI-TR', 'INGLÉS VI', 1, 6, 1, 'ITM-2010', 'TRC', 5),
('INGVII-TR', 'INGLÉS VII', 1, 7, 1, 'ITM-2010', 'TRC', 5),
('INGVIII-TR', 'INGLÉS VII', 1, 8, 1, 'ITM-2010', 'TRC', 5),
('INM-ES', 'INGENIERIA DE METODOS', 1, 4, 5, 'ITM-2010', 'TRC', 5),
('INO-ES', 'INVESTIGACION DE OPERACIONES', 1, 9, 4, 'ITM-2010', 'TRC', 5),
('INP-ES', 'INGENIERIA DE PLANTA', 1, 6, 4, 'ITM-2010', 'TRC', 6),
('INP-ESP', 'INGENIERIA DE PLASTICOS', 1, 7, 6, 'ITM-2010', 'TRC', 5),
('LPN-CV', 'LOGICA DE PROGRAMACION NUMERICA', 1, 3, 7, 'ITM-2010', 'TRC', 5),
('MAE-ES', 'MANUFACTURA ESBELTA', 1, 7, 2, 'ITM-2010', 'TRC', 5),
('MEC-ES', 'MECANICA', 1, 4, 3, 'ITM-2010', 'TRC', 7),
('MECA-ES', 'MECATRONICA', 1, 9, 3, 'ITM-2010', 'TRC', 6),
('MED-ES', 'METODOLOGIAS DE DISEÑO', 1, 8, 4, 'ITM-2010', 'TRC', 5),
('MEF-ES', 'MECANICA DE FLUIDOS', 1, 5, 7, 'ITM-2010', 'TRC', 5),
('MEI-CV', 'METODOLOGIAS DE LA INVESTIGACION', 1, 7, 5, 'ITM-2010', 'TRC', 5),
('MET-CV', 'METROLOGIA', 1, 2, 7, 'ITM-2010', 'TRC', 5),
('PCP-ES', 'PLANEACION Y CONTROL DE LA PRODUCCION', 1, 5, 4, 'ITM-2010', 'TRC', 5),
('PEI-CV', 'PROBABILIDAD Y ESTADISTICA INFERENCIAL', 1, 2, 6, 'ITM-2010', 'TRC', 7),
('PEM-ES', 'PROCESOS ESPECIALES DE MANUFACTURA', 1, 9, 6, 'ITM-2010', 'TRC', 6),
('PPM-ES', 'PROCESOS PRIMARIOS DE MANUFACTURA', 1, 5, 5, 'ITM-2010', 'TRC', 7),
('PRE-ESP', 'PROCESOS DE ENSAMBLE', 1, 8, 6, 'ITM-2010', 'TRC', 6),
('PRI-ES', 'PRONOSTICOS E INVENTARIOS', 1, 4, 4, 'ITM-2010', 'TRC', 5),
('PSM-ES', 'PROCESOS SECUNDARIOS DE MANUFACTURA', 1, 6, 5, 'ITM-2010', 'TRC', 6),
('REM-CV', 'RESISTENCIA DE MATERIALES', 1, 7, 4, 'ITM-2010', 'TRC', 6),
('SHI-CV', 'SEGURIDAD E HIGIENE INDUSTRIAL', 1, 1, 6, 'ITM-2010', 'TRC', 5),
('SNH-ES', 'SISTEMAS NEUMATICOS E HIDRAULICOS', 1, 7, 3, 'ITM-2010', 'TRC', 6),
('SPD-ES', 'SIMULACION DE PROCESOS DISCRETOS', 1, 9, 7, 'ITM-2010', 'TRC', 6),
('TER-CV', 'TERMODINAMICA', 1, 3, 4, 'ITM-2010', 'TRC', 5),
('TSD-ES', 'TECNOLOGIAS DE SOPORTE EN DISEÑO Y MANUFACTURA', 1, 8, 7, 'ITM-2010', 'TRC', 6),
('VAS-TR', 'VALORES DEL SER', 1, 1, 2, 'ITM-2010', 'TRC', 3),
('AFIN-1', 'ADMINISTRACION FINANCIERA', 1, 8, 2, 'ITM-2018', 'TRC', 4),
('AL-1', 'ALGEBRA LINEAL', 1, 1, 4, 'ITM-2018', 'TRC', 6),
('CDIF-1', 'CALCULO DIFERENCIAL', 1, 2, 3, 'ITM-2018', 'TRC', 4),
('CINT-1', 'CALCULO INTEGRAL', 1, 3, 3, 'ITM-2018', 'TRC', 4),
('CLPR-1', 'CONTROLADORES LOGICOS PROGRAMABLES', 1, 8, 3, 'ITM-2018', 'TRC', 7),
('DHYV-1', 'DESARROLLO HUMANO Y VALORES', 1, 1, 2, 'ITM-2018', 'TRC', 3),
('DIN-1', 'DIBUJO INDUSTRIAL', 1, 2, 6, 'ITM-2018', 'TRC', 7),
('DLPR-1', 'DISEÑO DEL PRODUCTO', 1, 7, 4, 'ITM-2018', 'TRC', 5),
('DPI-1', 'DIBUJO PARA INGENIERIA', 1, 3, 6, 'ITM-2018', 'TRC', 7),
('DYMAC-1', 'DISEÑO Y MANUFACTURA ASISTIDA POR COMPUTADORA', 1, 8, 5, 'ITM-2018', 'TRC', 6),
('E-1', 'ESTANCIA I', 1, 4, 7, 'ITM-2018', 'TRC', 8),
('E-2', 'ESTANCIA II', 1, 7, 7, 'ITM-2018', 'TRC', 8),
('EDTR-1', 'ESTUDIO DEL TRABAJO', 1, 2, 7, 'ITM-2018', 'TRC', 8),
('EOYE-1', 'EXPRESION ORAL Y ESCRITA I', 1, 1, 7, 'ITM-2018', 'TRC', 5),
('EOYE-2', 'EXPRESION ORAL Y ESCRITA II', 1, 9, 7, 'ITM-2018', 'TRC', 5),
('EPR-1', 'ETICA PROFESIONAL', 1, 4, 2, 'ITM-2018', 'TRC', 3),
('EYEI-1', 'ELECTRICIDAD Y ELECTRONICA INDUSTRIAL', 1, 6, 4, 'ITM-2018', 'TRC', 8),
('EYM-1', 'ELECTRICIDAD Y MAGNETISMO', 1, 5, 4, 'ITM-2018', 'TRC', 5),
('EYPR-1', 'ESTRUCTURA Y PROPIEDADES DE LOS MATERIALES', 1, 5, 6, 'ITM-2018', 'TRC', 5),
('FDLC-1', 'FUNDAMENTOS DE LA CALIDAD', 1, 2, 5, 'ITM-2018', 'TRC', 7),
('FDM-1', 'FUNDAMENTOS DE MECANICA', 1, 5, 5, 'ITM-2018', 'TRC', 8),
('FIS-1', 'FISICA', 1, 4, 4, 'ITM-2018', 'TRC', 4),
('FMAT-1', 'FUNCIONES MATEMATICAS', 1, 1, 3, 'ITM-2018', 'TRC', 4),
('FPIN-1', 'FISICA PARA INGENIERIA', 1, 7, 2, 'ITM-2018', 'TRC', 4),
('FYEDP-1', 'FORMULACION Y EVALUACION DE PROYECTOS', 1, 9, 2, 'ITM-2018', 'TRC', 6),
('GDLC-1', 'GESTION DE LA CALIDAD', 1, 3, 5, 'ITM-2018', 'TRC', 7),
('HCYC-1', 'HABILIDADES CONGNITIVAS Y CREATIVIDAD', 1, 3, 2, 'ITM-2018', 'TRC', 3),
('HGER-1', 'HABILIDADES GERENCIALES', 1, 5, 2, 'ITM-2018', 'TRC', 3),
('IAPC-1', 'INGENIERIA ASISTIDA POR COMPUTADORA', 1, 8, 4, 'ITM-2018', 'TRC', 6),
('IDO-1', 'INVESTIGACION DE OPERACIONES', 1, 8, 6, 'ITM-2018', 'TRC', 5),
('IDPL-1', 'INGENIERIA DE PLASTICOS', 1, 4, 6, 'ITM-2018', 'TRC', 7),
('IEM-1', 'INTELIGENCIA EMOCIONAL Y MANEJO DE CONFLICTOS', 1, 2, 2, 'ITM-2018', 'TRC', 3),
('LDPM-1', 'LOGICA DIGITAL PARA LA MANUFACTURA', 1, 7, 3, 'ITM-2018', 'TRC', 5),
('LEAD-1', 'LIDERAZGO DE EQUIPOS DE ALTO DESEMPEÑO', 1, 6, 2, 'ITM-2018', 'TRC', 3),
('LSS-1', 'LEAN SIX-SIGMA', 1, 9, 4, 'ITM-2018', 'TRC', 8),
('MIN-1', 'MANTENIMIENTO INDUSTRIAL', 1, 6, 6, 'ITM-2018', 'TRC', 5),
('MIPC-1', 'MANUFACTURA INTEGRADA POR COMPUTADORAS', 1, 9, 3, 'ITM-2018', 'TRC', 6),
('MMPM-1', 'MECANICA DE MATERIALES PARA LA MANUFACTURA', 1, 6, 5, 'ITM-2018', 'TRC', 8),
('MPI-1', 'MATEMATICAS PARA INGENIERIA I', 1, 4, 3, 'ITM-2018', 'TRC', 4),
('MPI-2', 'MATEMATICAS PARA INGENIERIA II', 1, 5, 3, 'ITM-2018', 'TRC', 5),
('MPM-1', 'METROLOGIA PARA LA MANUFACTURA', 1, 1, 5, 'ITM-2018', 'TRC', 8),
('PEDM-1', 'PROCESOS ESPECIALES DE MANUFACTURA', 1, 6, 7, 'ITM-2018', 'TRC', 7),
('PEIN-1', 'PRONOSTICOS E INVENTARIOS', 1, 7, 6, 'ITM-2018', 'TRC', 5),
('PPDM-1', 'PROCESOS PRIMARIOS DE MANUFACTURA', 1, 3, 7, 'ITM-2018', 'TRC', 8),
('PSDM-1', 'PROCESOS SECUNDARIOS DE MANUFACTURA', 1, 5, 7, 'ITM-2018', 'TRC', 8),
('PYES-1', 'PROBABILIDAD Y ESTADISTICA', 1, 2, 4, 'ITM-2018', 'TRC', 5),
('QB-1', 'QUIMICA BASICA', 1, 3, 4, 'ITM-2018', 'TRC', 5),
('SADC-1', 'SISTEMAS AVANZADOS DE LA CALIDAD', 1, 4, 5, 'ITM-2018', 'TRC', 8),
('SDPM-1', 'SIMULACION DE PROCESOS DE MANUFACTURA', 1, 9, 6, 'ITM-2018', 'TRC', 5),
('SDPR-1', 'SISTEMAS DE PRODUCCION', 1, 8, 7, 'ITM-2018', 'TRC', 6),
('SHYS-1', 'SEGURIDAD, HIGIENE Y SUSTENTABILIDAD', 1, 1, 6, 'ITM-2018', 'TRC', 8),
('SNEH-1', 'SISTEMAS NEUMATICOS E HIDRAULICOS', 1, 7, 5, 'ITM-2018', 'TRC', 7),
('TER-1', 'TERMODINAMICA', 1, 6, 3, 'ITM-2018', 'TRC', 3),
('TNTM-1', 'TOPICOS DE NUEVAS TECNOLOGIAS DE MANUFACTURA', 1, 9, 5, 'ITM-2018', 'TRC', 4),
('AAD-1', 'AUDITORIA ADMINISTRATIVA', 1, 7, 4, 'PyMES-2014', 'TRC', 4),
('ADCH-1', 'ADMINISTRACION DEL CAPITAL HUMANO', 1, 4, 4, 'PyMES-2014', 'TRC', 6),
('ADEF-1', 'ADMINISTRACION DE EMPRESAS FAMILIARES', 1, 9, 4, 'PyMES-2014', 'TRC', 5),
('ADFIN-1', 'ADMINISTRACION FINANCIERA', 1, 7, 5, 'PyMES-2014', 'TRC', 6),
('ADLPR-1', 'ADMINISTRACION DE LA PRODUCCION', 1, 7, 2, 'PyMES-2014', 'TRC', 5),
('ADSI-1', 'ADMINISTRACION DE SISTEMAS DE INFORMACION', 1, 2, 6, 'PyMES-2014', 'TRC', 6),
('AFIN-1', 'ANALISIS FINANCIERO', 1, 6, 5, 'PyMES-2014', 'TRC', 7),
('ALDO-1', ' ASPECTOS LEGALES DE ORGANIZACION', 1, 2, 7, 'PyMES-2014', 'TRC', 5),
('ASYS-1', 'ADMINISTRACION DE SUELDOS Y SALARIOS', 1, 6, 4, 'PyMES-2014', 'TRC', 6),
('CAD-1', 'CONTABILIDAD ADMINISTRATIVA', 1, 4, 5, 'PyMES-2014', 'TRC', 6),
('CAL-1', 'CALIDAD', 1, 8, 2, 'PyMES-2014', 'TRC', 6),
('CDC-1', 'CONTABILIDAD DE COSTOS', 1, 3, 5, 'PyMES-2014', 'TRC', 7),
('CELEC-1', 'COMERCIO ELECTRONICO', 1, 7, 6, 'PyMES-2014', 'TRC', 5),
('CEYA-1', 'COMERCIO EXTERIOR Y ADUANAS', 1, 9, 5, 'PyMES-2014', 'TRC', 5),
('CFIN-1', 'CONTABILIDAD FINANCIERA', 1, 2, 5, 'PyMES-2014', 'TRC', 6),
('CFIS-1', 'CONTRIBUCIONES FISCALES', 1, 8, 3, 'PyMES-2014', 'TRC', 6),
('CINT-1', 'COMERCIO INTERNACIONAL', 1, 7, 3, 'PyMES-2014', 'TRC', 6),
('CONS-1', 'CONSULTORIA', 1, 9, 3, 'PyMES-2014', 'TRC', 6),
('CYDO-1', 'COMPORTAMIENTO Y DESARROLLO ORGANIZACIONAL', 1, 5, 4, 'PyMES-2014', 'TRC', 6),
('DEMP-1', 'DESARROLLO EMPRENDEDOR', 1, 8, 6, 'PyMES-2014', 'TRC', 5),
('DINT-1', 'DESARROLLO INTRAPERSONAL', 1, 3, 2, 'PyMES-2014', 'TRC', 3),
('DLAB-1', 'DERECHO LABORAL', 1, 5, 7, 'PyMES-2014', 'TRC', 6),
('DSUS-1', 'DESARROLLO SUSTENTABLE', 1, 9, 2, 'PyMES-2014', 'TRC', 6),
('E-1', 'ESTANCIA I', 1, 4, 7, 'PyMES-2014', 'TRC', 8),
('E-2', 'ESTANCIA II', 1, 7, 7, 'PyMES-2014', 'TRC', 8),
('EOYE-1', 'EXPRESION ORAL Y ESCRITA', 1, 1, 7, 'PyMES-2014', 'TRC', 6),
('EPRO-1', 'ETICA PROFESIONAL', 1, 6, 2, 'PyMES-2014', 'TRC', 3),
('FDCON-1', 'FUNDAMENTOS DE CONTABILIDAD', 1, 1, 5, 'PyMES-2014', 'TRC', 6),
('FDMER-1', 'FUNDAMENTOS DE MERCADOTECNIA', 1, 4, 6, 'PyMES-2014', 'TRC', 5),
('FRAN-1', 'FRANQUICIAS', 1, 9, 6, 'PyMES-2014', 'TRC', 6),
('FYEPI-1', 'FORMULACION Y EVALUACION DE PROYECTOS DE INVERSION', 1, 8, 5, 'PyMES-2014', 'TRC', 6),
('HDPEN-1', 'HABILIDADES DEL PENSAMIENTO', 1, 4, 2, 'PyMES-2014', 'TRC', 3),
('HOF-1', 'HERRAMIENTAS OFIMATICAS', 1, 1, 6, 'PyMES-2014', 'TRC', 6),
('HORG-1', 'HABILIDADES ORGANIZACIONALES', 1, 5, 2, 'PyMES-2014', 'TRC', 3),
('I-1', 'INGLES I', 1, 1, 1, 'PyMES-2014', 'TRC', 5),
('I-2', 'INGLES II', 1, 2, 1, 'PyMES-2014', 'TRC', 5),
('I-3', 'INGLES III', 1, 3, 1, 'PyMES-2014', 'TRC', 5),
('I-4', 'INGLES IV', 1, 4, 1, 'PyMES-2014', 'TRC', 5),
('I-5', 'INGLES V', 1, 5, 1, 'PyMES-2014', 'TRC', 5),
('I-6', 'INGLES VI', 1, 6, 1, 'PyMES-2014', 'TRC', 5),
('I-7', 'INGLES VII', 1, 7, 1, 'PyMES-2014', 'TRC', 5),
('I-8', 'INGLES VIII', 1, 8, 1, 'PyMES-2014', 'TRC', 5),
('I-9', 'INGLES IX', 1, 9, 1, 'PyMES-2014', 'TRC', 5),
('IAAD-1', 'INTRODUCCION A LA ADMINISTRACION', 1, 1, 4, 'PyMES-2014', 'TRC', 6),
('IALM-1', 'INTRODUCCION A LAS MATEMATICAS', 1, 1, 3, 'PyMES-2014', 'TRC', 7),
('IDMER-1', 'INVESTIGACION DE MERCADOS', 1, 5, 6, 'PyMES-2014', 'TRC', 6),
('IEM-1', 'INTELIGENCIA EMOCIONAL', 1, 2, 2, 'PyMES-2014', 'TRC', 3),
('LAD-1', 'LOGICA ADMINISTRATIVA', 1, 8, 7, 'PyMES-2014', 'TRC', 6),
('MAAD-1', 'MATEMATICAS APLICADAS A LA ADMINISTRACION', 1, 2, 3, 'PyMES-2014', 'TRC', 7),
('MACR-1', 'MACROECONOMIA', 1, 4, 3, 'PyMES-2014', 'TRC', 6),
('MCYP-1', 'METODOS CUANTITATIVOS Y PRONOSTICOS', 1, 6, 3, 'PyMES-2014', 'TRC', 6),
('MDLI-1', 'METODOLOGIA DE LA INVESTIGACION', 1, 3, 7, 'PyMES-2014', 'TRC', 6),
('MEST-1', 'MERCADOTECNIA ESTRATEGICA', 1, 6, 6, 'PyMES-2014', 'TRC', 6),
('MFIN-1', 'MATEMATICAS FINANCIERAS', 1, 5, 3, 'PyMES-2014', 'TRC', 7),
('MICR-1', 'MICROECONOMIA', 1, 3, 6, 'PyMES-2014', 'TRC', 6),
('NEM-1', 'NEGOCIACION EMPRESARIAL', 1, 5, 5, 'PyMES-2014', 'TRC', 6),
('PAD-1', 'PROCESO ADMINISTRATIVO', 1, 2, 4, 'PyMES-2014', 'TRC', 6),
('PEST-1', 'PLANEACION ESTRATEGICA', 1, 3, 4, 'PyMES-2014', 'TRC', 6),
('PYDSEN-1', 'PLANEACION Y DISEÑO DE SISTEMAS Y ESTRATEGAS DE NEGOCIOS', 1, 9, 7, 'PyMES-2014', 'TRC', 5),
('PYEST-1', 'PROBABILIDAD Y ESTADISTICA', 1, 3, 3, 'PyMES-2014', 'TRC', 6),
('SHG-1', 'SEMINARIO DE HABILIDADES GERENCIALES', 1, 8, 4, 'PyMES-2014', 'TRC', 5),
('TIAN-1', 'TECNOLOGIAS DE LA INFORMACION APLICADA A LOS NEGOCIOS', 1, 6, 7, 'PyMES-2014', 'TRC', 6),
('VDS-1', 'VALORES DEL SER', 1, 1, 2, 'PyMES-2014', 'TRC', 3),
('I-1', 'INGLES I', 1, 1, 1, 'PyMES-2018', 'TRC', 5),
('I-2', 'INGLES II', 1, 2, 1, 'PyMES-2018', 'TRC', 5),
('I-3', 'INGLES III', 1, 3, 1, 'PyMES-2018', 'TRC', 5),
('I-4', 'INGLES IV', 1, 4, 1, 'PyMES-2018', 'TRC', 5),
('I-5', 'INGLES V', 1, 5, 1, 'PyMES-2018', 'TRC', 5),
('I-6', 'INGLES VI', 1, 6, 1, 'PyMES-2018', 'TRC', 5),
('I-7', 'INGLES VII', 1, 7, 1, 'PyMES-2018', 'TRC', 5),
('I-8', 'INGLES VIII', 1, 8, 1, 'PyMES-2018', 'TRC', 5),
('I-9', 'INGLES IX', 1, 9, 1, 'PyMES-2018', 'TRC', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia_usuario`
--

DROP TABLE IF EXISTS `materia_usuario`;
CREATE TABLE IF NOT EXISTS `materia_usuario` (
  `clv_materia` varchar(10) NOT NULL,
  `clv_plan` varchar(10) NOT NULL,
  `clv_usuario` varchar(50) NOT NULL,
  `puntos_confianza` tinyint(4) DEFAULT '0',
  `puntos_director` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`clv_plan`,`clv_materia`,`clv_usuario`),
  KEY `usuario_materiaUsuario_FK` (`clv_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia_usuario2`
--

DROP TABLE IF EXISTS `materia_usuario2`;
CREATE TABLE IF NOT EXISTS `materia_usuario2` (
  `clv_materia` varchar(10) NOT NULL,
  `clv_plan` varchar(10) NOT NULL,
  `clv_usuario` varchar(50) NOT NULL,
  `puntos_confianza` tinyint(4) DEFAULT NULL,
  `puntos_director` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materia_usuario2`
--

INSERT INTO `materia_usuario2` (`clv_materia`, `clv_plan`, `clv_usuario`, `puntos_confianza`, `puntos_director`) VALUES
('A-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 5),
('AC-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('ACC-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 2),
('ADOO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('ADS-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('AE-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 4),
('AL-1', 'ITI-2010', 'spolancom@upv.edu.mx', 5, 5),
('ASI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 0),
('ASO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 4),
('CDI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 0),
('CDM-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 1),
('DE-1', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 0),
('DI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 5, 5),
('DIN-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 5),
('E-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 0),
('E-2', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 0),
('ED-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 4),
('EP-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 3),
('FF-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 2),
('FR-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 4),
('FSI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('GCA-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('HM-1', 'ITI-2010', 'spolancom@upv.edu.mx', 5, 5),
('HO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 2),
('HOFI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 3),
('HP-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('I-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 1),
('I-2', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('I-3', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 3),
('I-4', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 1),
('I-5', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 4),
('I-6', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('I-7', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 4),
('I-8', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 5),
('I-9', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 3),
('IBD-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 2),
('IE-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 3),
('IGC-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 2),
('IMBD-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('IN-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 3),
('INTI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 1),
('IPOO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 5, 5),
('IR-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 0),
('IS-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 0),
('ITI-2', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 0),
('LC-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('MB-1', 'ITI-2010', 'spolancom@upv.edu.mx', 5, 0),
('MD-1', 'ITI-2010', 'spolancom@upv.edu.mx', 0, 0),
('MDA-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 5),
('NE-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('PA-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 4),
('PE-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 3),
('POO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 5),
('PRES-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 1),
('PTI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 0),
('PW-1', 'ITI-2010', 'spolancom@upv.edu.mx', 1, 5),
('RTO-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('SEGI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 2),
('SI-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 4),
('SW-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 1),
('TAW-1', 'ITI-2010', 'spolancom@upv.edu.mx', 3, 3),
('TW-1', 'ITI-2010', 'spolancom@upv.edu.mx', 2, 1),
('VDS-1', 'ITI-2010', 'spolancom@upv.edu.mx', 4, 4),
('ADM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ADP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ALL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('AUT-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CAL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('CAV-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CCP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('CDI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CIM-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('DEI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('DII-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('DME-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('E-1', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('E-2', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('ECD-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ETP-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('FEL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('FEP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('FUE-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('FUQ-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('GEC-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('GEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('HAO-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('HAP-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('HEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('HEO-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('IIM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INE-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGII-TR  ', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INGIII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGIV-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('INGIX-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGV-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGVI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGVII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INGVIII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('INM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INO-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('INP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INP-ESP', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('LPN-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MAE-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MEC-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MECA-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MED-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MEF-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('MEI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MET-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('PCP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('PEI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('PEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('PPM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('PRE-ESP', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('PRI-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('PSM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('REM-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('SHI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('SNH-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('SPD-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('TER-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('TSD-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('VAS-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('AFIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('AL-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('CDIF-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('CINT-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('CLPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DHYV-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DLPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('DPI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('DYMAC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('E-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('E-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('EDTR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('EOYE-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EOYE-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EYEI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('EYM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('EYPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('FDLC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('FDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('FIS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('FMAT-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('FPIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('FYEDP-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('GDLC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('HCYC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('HGER-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IAPC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('IDO-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IDPL-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IEM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('LDPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('LEAD-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('LSS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MIPC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('MMPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('MPI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MPI-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('MPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('PEDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('PEIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('PPDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('PSDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('PYES-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('QB-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('SADC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('SDPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('SDPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 0),
('SHYS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('SNEH-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('TER-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('TNTM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 5),
('ADM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ADP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ALL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('AUT-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CAL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('CAV-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CCP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('CDI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('CIM-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('DEI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('DII-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('DME-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('E-1', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('E-2', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('ECD-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('ETP-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('FEL-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('FEP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('FUE-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('FUQ-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('GEC-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('GEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('HAO-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('HAP-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('HEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('HEO-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('IIM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INE-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGII-TR  ', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INGIII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGIV-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('INGIX-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGV-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INGVI-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INGVII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('INGVIII-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('INM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('INO-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('INP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('INP-ESP', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('LPN-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MAE-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MEC-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('MECA-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MED-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MEF-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('MEI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('MET-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('PCP-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('PEI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('PEM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('PPM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('PRE-ESP', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('PRI-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 4),
('PSM-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 3),
('REM-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 5),
('SHI-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('SNH-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('SPD-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('TER-CV', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 1),
('TSD-ES', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 0),
('VAS-TR', 'ITM-2010', 'aperezf@upv.edu.mx', 0, 2),
('AFIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('AL-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('CDIF-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('CINT-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('CLPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DHYV-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('DLPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('DPI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('DYMAC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('E-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('E-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('EDTR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('EOYE-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EOYE-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('EYEI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('EYM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('EYPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('FDLC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('FDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('FIS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('FMAT-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('FPIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('FYEDP-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('GDLC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('HCYC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('HGER-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IAPC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('IDO-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IDPL-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('IEM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('LDPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('LEAD-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('LSS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MIPC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('MMPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('MPI-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('MPI-2', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('MPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('PEDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('PEIN-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('PPDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('PSDM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('PYES-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('QB-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 1),
('SADC-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('SDPM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 4),
('SDPR-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 0),
('SHYS-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('SNEH-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 2),
('TER-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 3),
('TNTM-1', 'ITM-2018', 'aperezf@upv.edu.mx', 0, 5),
('A-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('AC-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('ACC-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('ADOO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('ADS-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('AE-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('AL-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('ASI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('ASO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('CDI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('CDM-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('DE-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('DI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('DIN-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('E-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('E-2', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('ED-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('EP-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('FF-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('FR-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('FSI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('GCA-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('HM-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('HO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('HOFI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('HP-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('I-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('I-2', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('I-3', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('I-4', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('I-5', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('I-6', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('I-7', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('I-8', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('I-9', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('IBD-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('IE-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('IGC-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('IMBD-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('IN-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('INTI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('IPOO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('IR-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('IS-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('ITI-2', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('LC-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('MB-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('MD-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('MDA-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('NE-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('PA-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('PE-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('POO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('PRES-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('PTI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('PW-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('RTO-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('SEGI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 1),
('SI-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 5),
('SW-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 4),
('TAW-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('TW-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 3),
('VDS-1', 'ITI-2010', 'corozcog@upv.edu.mx', 0, 2),
('AAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ADCH-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ADEF-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ADFIN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ADLPR-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ADSI-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('AFIN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ALDO-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('ASYS-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CAL-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CDC-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CELEC-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CEYA-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CFIN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CFIS-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CINT-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CONS-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('CYDO-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('DEMP-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('DINT-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('DLAB-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('DSUS-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('E-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('E-2', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('EOYE-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('EPRO-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('FDCON-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('FDMER-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('FRAN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('FYEPI-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('HDPEN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('HOF-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('HORG-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-2', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-3', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-4', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-5', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-6', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-7', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-8', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-9', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('IAAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('IALM-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('IDMER-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('IEM-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('LAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MAAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MACR-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MCYP-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MDLI-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MEST-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MFIN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('MICR-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('NEM-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('PAD-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('PEST-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('PYDSEN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('PYEST-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('SHG-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('TIAN-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('VDS-1', 'PyMES-2014', 'etorresr@upv.edu.mx', 0, 0),
('I-1', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-2', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-3', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-4', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-5', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-6', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-7', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-8', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('I-9', 'PyMES-2018', 'etorresr@upv.edu.mx', 0, 0),
('ADM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('ADPR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('AIND-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('AL-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('AYPDDS-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CAV-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CDIF-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CDM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CDME-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CDROB-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CEYE-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CINT-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CIROB-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('CLP-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('DDROB-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('DDSM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('DHV-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('DPIN-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('DYSEM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('E-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('E-2', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('EOYE-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('EOYE-2', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('EPR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('EYMAG-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('EYPDM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('FIS-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('FMAT-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('FPIN-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('HCOM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('HCYC-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('HGER-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-2', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-3', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-4', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-5', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-6', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-7', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-8', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('I-9', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('IAPCOM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('IDCON-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('IEYMC-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('IIMYR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('ISMYR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('LDEAD-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MDCR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MDSMYR-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MET-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MPI-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MPI-2', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('MYSDS-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PDMAN-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PDPER-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PDRI-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PDSE-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PEST-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('PYES-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('QM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('RDMAT-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SADM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SDIG-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SDPI-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SDVA-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SEDI-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SNEH-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SYAC-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('SYMA-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('TERM-1', 'IM-2018', 'yhernandezm@upv.edu.mx', 0, 0),
('ADM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('ADP-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('ALL-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('AUT-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('CAL-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('CAV-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('CCP-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('CDI-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('CIM-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('DEI-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('DII-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('DME-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('E-1', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('E-2', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('ECD-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('ETP-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('FEL-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('FEP-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('FUE-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('FUQ-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('GEC-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('GEM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('HAO-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('HAP-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('HEM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('HEO-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('IIM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('INE-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('INGI-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('INGII-TR  ', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('INGIII-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('INGIV-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('INGIX-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('INGV-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('INGVI-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('INGVII-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('INGVIII-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('INM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('INO-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('INP-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('INP-ESP', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('LPN-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 5),
('MAE-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('MEC-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('MECA-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('MED-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 5),
('MEF-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('MEI-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('MET-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 5),
('PCP-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('PEI-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('PEM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('PPM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('PRE-ESP', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('PRI-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('PSM-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('REM-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('SHI-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('SNH-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 2),
('SPD-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 4),
('TER-CV', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('TSD-ES', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 1),
('VAS-TR', 'ITM-2010', 'yvillasenorp@upv.edu.mx', 0, 3),
('AFIN-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('AL-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('CDIF-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('CINT-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('CLPR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('DHYV-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('DIN-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('DLPR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('DPI-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('DYMAC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('E-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('E-2', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 5),
('EDTR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('EOYE-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('EOYE-2', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 0),
('EPR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('EYEI-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 5),
('EYM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('EYPR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('FDLC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('FDM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('FIS-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('FMAT-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('FPIN-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('FYEDP-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('GDLC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('HCYC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('HGER-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 5),
('IAPC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('IDO-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('IDPL-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('IEM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('LDPM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('LEAD-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('LSS-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 5),
('MIN-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('MIPC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 5),
('MMPM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('MPI-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('MPI-2', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('MPM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('PEDM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('PEIN-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('PPDM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('PSDM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('PYES-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('QB-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('SADC-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('SDPM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('SDPR-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('SHYS-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 4),
('SNEH-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 2),
('TER-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 3),
('TNTM-1', 'ITM-2018', 'yvillasenorp@upv.edu.mx', 0, 1),
('A-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('AC-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ACC-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ADOO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ADS-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('AE-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('AL-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ASI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ASO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('CDI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('CDM-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('DE-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('DI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('DIN-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('E-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('E-2', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ED-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('EP-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('FF-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('FR-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('FSI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('GCA-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('HM-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('HO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('HOFI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('HP-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-2', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-3', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-4', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-5', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-6', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-7', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-8', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('I-9', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IBD-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IE-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IGC-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IMBD-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IN-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('INTI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IPOO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IR-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('IS-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ITI-2', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('LC-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('MB-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('MD-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('MDA-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('NE-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('PA-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('PE-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('POO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('PRES-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('PTI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('PW-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('RTO-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('SEGI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('SI-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('SW-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('TAW-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('TW-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('VDS-1', 'ITI-2010', 'haviles@upv.edu.mx', 0, 0),
('ADM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('ADPR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('AIND-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('AL-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('AYPDDS-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CAV-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CDIF-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CDM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CDME-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CDROB-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CEYE-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CINT-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CIROB-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('CLP-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('DDROB-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('DDSM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('DHV-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('DPIN-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('DYSEM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('E-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('E-2', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('EOYE-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('EOYE-2', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('EPR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('EYMAG-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('EYPDM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('FIS-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('FMAT-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('FPIN-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('HCOM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('HCYC-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('HGER-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-2', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-3', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-4', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-5', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-6', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-7', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-8', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('I-9', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('IAPCOM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('IDCON-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('IEYMC-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('IIMYR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('ISMYR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('LDEAD-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MDCR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MDSMYR-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MET-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MPI-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MPI-2', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('MYSDS-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PDMAN-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PDPER-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PDRI-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PDSE-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PEST-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('PYES-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('QM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('RDMAT-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SADM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SDIG-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SDPI-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SDVA-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SEDI-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SNEH-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SYAC-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('SYMA-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('TERM-1', 'IM-2018', 'haviles@upv.edu.mx', 0, 0),
('ADM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('ADP-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('ALL-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('AUT-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('CAL-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('CAV-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('CCP-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('CDI-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('CIM-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('DEI-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('DII-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('DME-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('E-1', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('E-2', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('ECD-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('ETP-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('FEL-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('FEP-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('FUE-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('FUQ-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('GEC-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('GEM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('HAO-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('HAP-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('HEM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('HEO-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('IIM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INE-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGI-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGII-TR  ', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGIII-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGIV-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGIX-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGV-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGVI-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGVII-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INGVIII-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INO-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INP-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('INP-ESP', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('LPN-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MAE-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MEC-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MECA-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MED-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MEF-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MEI-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('MET-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PCP-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PEI-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PEM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PPM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PRE-ESP', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PRI-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('PSM-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('REM-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('SHI-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('SNH-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('SPD-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('TER-CV', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('TSD-ES', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('VAS-TR', 'ITM-2010', 'haviles@upv.edu.mx', 0, 0),
('AFIN-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('AL-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('CDIF-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('CINT-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('CLPR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('DHYV-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('DIN-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('DLPR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('DPI-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('DYMAC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('E-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('E-2', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EDTR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EOYE-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EOYE-2', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EPR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EYEI-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EYM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('EYPR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FDLC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FDM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FIS-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FMAT-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FPIN-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('FYEDP-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('GDLC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('HCYC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('HGER-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('IAPC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('IDO-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('IDPL-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('IEM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('LDPM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('LEAD-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('LSS-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MIN-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MIPC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MMPM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MPI-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MPI-2', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('MPM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('PEDM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('PEIN-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('PPDM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('PSDM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('PYES-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('QB-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('SADC-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('SDPM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('SDPR-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('SHYS-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('SNEH-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('TER-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0),
('TNTM-1', 'ITM-2018', 'haviles@upv.edu.mx', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_estudios`
--

DROP TABLE IF EXISTS `plan_estudios`;
CREATE TABLE IF NOT EXISTS `plan_estudios` (
  `clv_plan` varchar(10) NOT NULL,
  `nombre_plan` varchar(45) NOT NULL,
  `nivel` varchar(3) NOT NULL DEFAULT 'ING',
  `idcarrera` tinyint(4) NOT NULL,
  PRIMARY KEY (`clv_plan`),
  KEY `carrera_planEstudios_FK` (`idcarrera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `plan_estudios`
--

INSERT INTO `plan_estudios` (`clv_plan`, `nombre_plan`, `nivel`, `idcarrera`) VALUES
('IM-2010', 'IM-2010', 'ING', 2),
('IM-2018', 'IM-2018', 'ING', 2),
('IMC-2018', 'ASDAS', 'LIC', 2),
('ISA-2010', 'ISA-2010', 'ING', 3),
('ITI-2010', 'ITI-2010', 'ING', 1),
('ITI-2018', 'ITI-2018', 'ING', 1),
('ITM-2010', 'ITM-2010', 'ING', 4),
('ITM-2018', 'ITM-2018', 'ING', 4),
('PyMES-2014', 'PyMES-2014', 'LIC', 5),
('PyMES-2018', 'PyMES-2018', 'LIC', 5),
('Sdas', 'CVXC', 'LIC', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores_compartidos`
--

DROP TABLE IF EXISTS `profesores_compartidos`;
CREATE TABLE IF NOT EXISTS `profesores_compartidos` (
  `clv_usuario` varchar(50) NOT NULL,
  `id_carrera` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profesores_compartidos`
--

INSERT INTO `profesores_compartidos` (`clv_usuario`, `id_carrera`) VALUES
('haviles@upv.edu.mx', 2),
('haviles@upv.edu.mx', 3),
('haviles@upv.edu.mx', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `clv_usuario` varchar(50) NOT NULL,
  `pass_usuario` char(41) NOT NULL,
  `tipo_usuario` char(4) NOT NULL DEFAULT 'PROF',
  `idcarrera` tinyint(4) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `nivel_ads` varchar(5) NOT NULL DEFAULT 'Ing.',
  `contrato` varchar(3) NOT NULL DEFAULT 'PA',
  `telefono` char(10) DEFAULT NULL,
  `IMR` int(11) NOT NULL,
  PRIMARY KEY (`clv_usuario`),
  KEY `carrera_usuario_FK` (`idcarrera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`clv_usuario`, `pass_usuario`, `tipo_usuario`, `idcarrera`, `nombre_usuario`, `nivel_ads`, `contrato`, `telefono`, `IMR`) VALUES
('admin', '*4ACFE3202A5FF5CF467898FC58AAB1D615029441', 'ADMI', 1, 'admin', 'Dr.', 'PTC', NULL, 0),
('anonimo', '1234', 'NOSE', 4, 'anonimo', 'Ing.', 'PA', '8341654545', 45154),
('aperezf@upv.edu.mx', '*BD09BB38355F203EB22BD0D530248D4A7E73639D', 'DIRE', 4, 'Azucena Perez', 'M.C', 'PA', '', 0),
('corozcog@upv.edu.mx', '*2567782B7E56052F2703593E9DC0CAC796271A90', 'DIRE', 1, 'Carlos Orozco García', 'M.C', 'PA', '', 0),
('etorresr@upv.edu.mx', '*6A57BC20BBFA959D47EE7A42BBD493B879ADC37E', 'DIRE', 5, 'Estela Torres Ramírez', 'M.C', 'PA', '', 0),
('haviles@upv.edu.mx', 'haviles', 'PROF', 1, 'Hector Aviles', 'Dr.', 'PTC', '834', 60),
('spolancom@upv.edu.mx', '*3F50515DDEE62F18A2B1CE3BE819CFB2F3C869F1', 'PROF', 1, 'Said Polanco', 'Dr.', 'PTC', '', 100),
('yhernandezm@upv.edu.mx', '*C2103782757CDB9423F71C3884603496C807B9B9', 'DIRE', 2, 'Yahir Hernandez', 'Dr.', 'PTC', NULL, 0),
('yvillasenorp@upv.edu.mx', '*8A0A1FBA635F726F0AC10A92E7475B507AD37E0B', 'PROF', 4, 'Yanet Villaseñor Ponce', 'M.C', 'PA', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `usuarios_prof`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `usuarios_prof`;
CREATE TABLE IF NOT EXISTS `usuarios_prof` (
`clv_usuario` varchar(50)
,`pass_usuario` char(41)
,`idcarrera` tinyint(4)
,`nombre_usuario` varchar(50)
,`telefono` char(10)
,`IMR` int(11)
,`contrato` varchar(3)
,`tipo_usuario` char(4)
,`nivel_ads` varchar(5)
,`nombre_carrera` varchar(100)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `getusuarios`
--
DROP TABLE IF EXISTS `getusuarios`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `getusuarios`  AS  select `usuarios`.`clv_usuario` AS `clv_usuario`,`usuarios`.`pass_usuario` AS `pass_usuario`,`usuarios`.`tipo_usuario` AS `tipo_usuario`,`usuarios`.`idcarrera` AS `idcarrera` from `usuarios` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `usuarios_prof`
--
DROP TABLE IF EXISTS `usuarios_prof`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `usuarios_prof`  AS  select `u`.`clv_usuario` AS `clv_usuario`,`u`.`pass_usuario` AS `pass_usuario`,`u`.`idcarrera` AS `idcarrera`,`u`.`nombre_usuario` AS `nombre_usuario`,`u`.`telefono` AS `telefono`,`u`.`IMR` AS `IMR`,`u`.`contrato` AS `contrato`,`u`.`tipo_usuario` AS `tipo_usuario`,`u`.`nivel_ads` AS `nivel_ads`,`c`.`nombre_carrera` AS `nombre_carrera` from (`usuarios` `u` join `carrera` `c` on((`u`.`idcarrera` = `c`.`idcarrera`))) where (`u`.`tipo_usuario` = 'PROF') ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aula_equipo`
--
ALTER TABLE `aula_equipo`
  ADD CONSTRAINT `aula_equipo_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `aula_equipo_ibfk_2` FOREIGN KEY (`id_aula`) REFERENCES `aulas` (`id`);

--
-- Filtros para la tabla `disponibilidad`
--
ALTER TABLE `disponibilidad`
  ADD CONSTRAINT `usuarios_disponibilidad_FK` FOREIGN KEY (`clv_usuario`) REFERENCES `usuarios` (`clv_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias_equipo` (`id`);

--
-- Filtros para la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD CONSTRAINT `grupos` FOREIGN KEY (`clv_plan`) REFERENCES `plan_estudios` (`clv_plan`);

--
-- Filtros para la tabla `grupo_asignacion`
--
ALTER TABLE `grupo_asignacion`
  ADD CONSTRAINT `NombreFK` FOREIGN KEY (`clv_grupo`) REFERENCES `grupos` (`clv_grupo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `materias`
--
ALTER TABLE `materias`
  ADD CONSTRAINT `planEstudios_materia_FK` FOREIGN KEY (`clv_plan`) REFERENCES `plan_estudios` (`clv_plan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `materia_usuario`
--
ALTER TABLE `materia_usuario`
  ADD CONSTRAINT `materia_materiaUsiario_FK` FOREIGN KEY (`clv_plan`,`clv_materia`) REFERENCES `materias` (`clv_plan`, `clv_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_materiaUsuario_FK` FOREIGN KEY (`clv_usuario`) REFERENCES `usuarios` (`clv_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `plan_estudios`
--
ALTER TABLE `plan_estudios`
  ADD CONSTRAINT `carrera_planEstudios_FK` FOREIGN KEY (`idcarrera`) REFERENCES `carrera` (`idcarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `carrera_usuario_FK` FOREIGN KEY (`idcarrera`) REFERENCES `carrera` (`idcarrera`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
