package com.tienda.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.usuarios.modelo.TipoDocumento;

/**
 * Repositorio del catalogo de tipos de documento.
 * Se usa para verificar que el idTipoDocumento enviado exista antes de
 * intentar guardar, y asi devolver un 400 claro en lugar de un error de
 * llave foranea de la base de datos.
 */
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
}
