-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2018 a las 20:01:36
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wogazedb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

CREATE TABLE `calificacion` (
  `id_calificacion` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `calificacion` int(1) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `id_us` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `experiencia`
--

CREATE TABLE `experiencia` (
  `id_exp` int(11) NOT NULL,
  `experiencia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `experiencia`
--

INSERT INTO `experiencia` (`id_exp`, `experiencia`) VALUES
(0, 'Sin experiencia'),
(1, 'Menos de un año'),
(2, 'Entre 1 y 3 años'),
(3, 'Entre 4 y 6 años'),
(4, 'Entre 7 y 9 años'),
(5, 'Entre 10 y 13 años'),
(6, 'Entre 14 y 17 años'),
(7, 'Entre 18 y 20 años'),
(8, 'Más de 20 años'),
(9, 'Más de 25 años'),
(10, 'Más de 30 años'),
(11, 'Más de 40 años');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `titulo` varchar(65) NOT NULL,
  `descripcion` varchar(1500) NOT NULL,
  `area` varchar(35) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `fecha_limite` date NOT NULL,
  `presupuesto` double NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plataforma`
--

CREATE TABLE `plataforma` (
  `id_version` int(11) NOT NULL,
  `version` varchar(150) NOT NULL,
  `notas` varchar(5000) NOT NULL,
  `fecha` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propuesta`
--

CREATE TABLE `propuesta` (
  `id_propuesta` int(11) NOT NULL,
  `id_trabajador` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `descripcion` varchar(1500) NOT NULL,
  `lapso_dias` int(3) NOT NULL,
  `precio` double NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id_trabajador` int(11) NOT NULL,
  `id_us` int(11) NOT NULL,
  `ocupacion_p` varchar(30) NOT NULL,
  `habilidades` varchar(120) NOT NULL,
  `perfil_personal` varchar(350) NOT NULL,
  `tiempo_labor` varchar(35) NOT NULL,
  `habilidades_b` varchar(120) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajos`
--

CREATE TABLE `trabajos` (
  `id_trabajo` int(11) NOT NULL,
  `trabajo` varchar(45) NOT NULL,
  `descripcion` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `trabajos`
--

INSERT INTO `trabajos` (`id_trabajo`, `trabajo`, `descripcion`) VALUES
(0, 'Otros', 'Selección de trabajos para otras ramas no expuestas en nuestra plataforma.'),
(1, 'Carpintería', 'La finalidad de la carpintería consiste en modificar las características de la madera para construir objetos de utilidad. Los muebles de madera, como las mesas, las sillas y los escritorios, son un producto de la carpintería.'),
(2, 'Herrería', 'En la herrería lo que se hace fundamentalmente es trabajar el material del hierro para fabricar diversos objetos, o bien la restauración de algunos elementos que están hechos de este material.'),
(3, 'Electricista', 'Es especialista en instalaciones eléctricas, una cuestión imprescindible para que los individuos puedan tener, en sus casas y en otros lugares, electricidad que los provee de luz, y además permite el funcionamiento de artefactos de uso corriente.'),
(4, 'Artesanía', 'La artesanía es un arte, cuya característica principal es que el trabajo o la elaboración de objetos que se hacen de forma manual, se moldean distintos materiales, o se realizan adornos con objetivos financieros.'),
(5, 'Mecánica', 'Se aplica el concepto de mecánica para referirse a aquellas actividades manuales, que se realizan en forma idéntica y rutinaria sin razonamiento, haciendo una comparación con el trabajo que efectúa una máquina.'),
(6, 'Pintor', 'Persona que se dedica a pintar puertas, paredes, casas y superficies en general, ya sea manualmente o con ayuda de aerosoles.'),
(7, 'Mensajería', 'Persona que tiene por oficio realizar un servicio que consiste en ir a recoger un paquete a quien se lo requiere y seguidamente llevarlo y entregarlo a su destinatario, generalmente dentro de una misma población.'),
(8, 'Obrero', 'Persona que tiene por oficio hacer un trabajo manual o que requiere esfuerzo físico, en especial en el sector de la construcción.'),
(9, 'Plomería', 'La plomería es un concepto que se emplea para nombrar al trabajo que consiste en instalar, mantener y reparar las tuberías. A través de estas tuberías, es posible evacuar las aguas cloacales y abastecer a la población de agua potable.'),
(10, 'Sastreria', 'Se le conoce como sastre a aquella persona que tiene como oficio la confección de piezas en vestimenta, de esta forma entonces el sastre cose y corta pantalones, bermudas, camisas, chemises, sacos y demás prendas.'),
(11, 'Cerrajero', 'Los cerrajeros suministran, instalan y reparan cerraduras de casas, negocios y automóviles. Hacen llaves para que encajen en las cerraduras. Utilizan gran variedad de herramientas. También instalan avanzados sistemas de seguridad.'),
(12, 'Traductor', 'Es aquella persona que partiendo de un texto en un idioma lo convierte en un texto equivalente en otro idioma diferente. Para realizar esta actividad el traductor debe conocer a fondo las lenguas que traduce y, además, la cultura y la idiosincrasia.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_us` int(100) NOT NULL,
  `nombres` varchar(55) NOT NULL,
  `apellidos` varchar(60) NOT NULL,
  `tipo_id` varchar(30) NOT NULL,
  `num_id` bigint(15) NOT NULL,
  `celular` bigint(10) NOT NULL,
  `telefono` int(7) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `email` varchar(55) NOT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `clave` varchar(30) DEFAULT NULL,
  `fecha_registro` datetime NOT NULL,
  `ultima_conexion` datetime NOT NULL,
  `imagen` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calificacion`
--
ALTER TABLE `calificacion`
  ADD PRIMARY KEY (`id_calificacion`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_pedido_2` (`id_pedido`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_us` (`id_us`);

--
-- Indices de la tabla `experiencia`
--
ALTER TABLE `experiencia`
  ADD PRIMARY KEY (`id_exp`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `plataforma`
--
ALTER TABLE `plataforma`
  ADD PRIMARY KEY (`id_version`);

--
-- Indices de la tabla `propuesta`
--
ALTER TABLE `propuesta`
  ADD PRIMARY KEY (`id_propuesta`),
  ADD KEY `id_trabajador` (`id_trabajador`),
  ADD KEY `id_pedido` (`id_pedido`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id_trabajador`),
  ADD KEY `id_us` (`id_us`);

--
-- Indices de la tabla `trabajos`
--
ALTER TABLE `trabajos`
  ADD PRIMARY KEY (`id_trabajo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_us`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calificacion`
--
ALTER TABLE `calificacion`
  MODIFY `id_calificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `experiencia`
--
ALTER TABLE `experiencia`
  MODIFY `id_exp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `plataforma`
--
ALTER TABLE `plataforma`
  MODIFY `id_version` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `propuesta`
--
ALTER TABLE `propuesta`
  MODIFY `id_propuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id_trabajador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_us` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_us`) REFERENCES `usuario` (`id_us`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `calificacion` (`id_pedido`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `propuesta`
--
ALTER TABLE `propuesta`
  ADD CONSTRAINT `propuesta_ibfk_1` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`),
  ADD CONSTRAINT `propuesta_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`);

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`id_us`) REFERENCES `usuario` (`id_us`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
