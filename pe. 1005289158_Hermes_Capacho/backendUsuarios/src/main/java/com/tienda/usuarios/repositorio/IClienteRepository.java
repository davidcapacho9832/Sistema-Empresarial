package com.tienda.usuarios.repositorio;

import com.tienda.usuarios.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}