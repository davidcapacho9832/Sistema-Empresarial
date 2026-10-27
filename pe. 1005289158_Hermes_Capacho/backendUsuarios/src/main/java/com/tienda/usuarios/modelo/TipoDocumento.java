package com.tienda.usuarios.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de catalogo TipoDocumento.
 * Mapea la tabla "tipodocumento" de la base de datos tiendagenericaDAEII2026.
 * Valores cargados por el script: 5 = CEDULA, 6 = TARJETA, 7 = PASAPORTE.
 */
@Entity
@Table(name = "tipodocumento")
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    public TipoDocumento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TipoDocumento{id=" + id + ", tipo='" + tipo + "'}";
    }
}
