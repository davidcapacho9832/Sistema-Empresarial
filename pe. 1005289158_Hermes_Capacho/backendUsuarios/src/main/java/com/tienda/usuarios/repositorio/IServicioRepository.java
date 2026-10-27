package com.tienda.usuarios.repositorio;

import com.tienda.usuarios.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioRepository extends JpaRepository<Servicio, Integer> {
}