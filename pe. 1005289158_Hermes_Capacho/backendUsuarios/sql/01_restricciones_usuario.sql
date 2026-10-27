-- ---------------------------------------------------------------------
-- Script opcional pero recomendado.
--
-- La tabla "usuario" del script TiendaGenerica.sql no declara indices
-- UNIQUE, de modo que la unica defensa contra duplicados es la validacion
-- de la capa de servicio. Estas restricciones agregan la garantia en la
-- propia base de datos y cierran la condicion de carrera que se produce
-- cuando dos peticiones simultaneas envian el mismo documento.
--
-- Ejecutar SOLO despues de verificar que no existan duplicados previos:
--   SELECT numeroDocumento, COUNT(*) FROM usuario GROUP BY numeroDocumento HAVING COUNT(*) > 1;
--   SELECT email, COUNT(*) FROM usuario GROUP BY email HAVING COUNT(*) > 1;
--   SELECT nombreUsuario, COUNT(*) FROM usuario GROUP BY nombreUsuario HAVING COUNT(*) > 1;
--
-- Nota: los datos de ejemplo del script traen el documento 63124561
-- repetido en los usuarios 1 y 11, por lo que hay que corregir uno de
-- los dos antes de crear el indice de numeroDocumento.
-- ---------------------------------------------------------------------

USE `tiendagenericaDAEII2026`;

ALTER TABLE `usuario`
  ADD UNIQUE KEY `UK_USUARIO_DOCUMENTO` (`numeroDocumento`),
  ADD UNIQUE KEY `UK_USUARIO_EMAIL` (`email`),
  ADD UNIQUE KEY `UK_USUARIO_NOMBREUSUARIO` (`nombreUsuario`);
