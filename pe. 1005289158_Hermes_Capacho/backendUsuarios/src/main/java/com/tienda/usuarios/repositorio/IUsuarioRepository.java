package com.tienda.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.usuarios.modelo.Usuario;

/**
 * Repositorio de datos (DAO) para la entidad Usuario.
 * JpaRepository ya ofrece los metodos basicos del CRUD (findAll, findById,
 * save, deleteById, existsById), por lo que solo se declaran las consultas
 * derivadas que necesita la logica de negocio.
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNumeroDocumento(String numeroDocumento);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByNumeroDocumento(String numeroDocumento);

    boolean existsByEmail(String email);

    boolean existsByNombreUsuario(String nombreUsuario);
}
