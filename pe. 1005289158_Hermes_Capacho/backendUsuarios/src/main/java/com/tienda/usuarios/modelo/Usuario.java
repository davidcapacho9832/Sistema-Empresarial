package com.tienda.usuarios.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad de dominio Usuario.
 * Mapea la tabla "usuario" de la base de datos tiendagenericaDAEII2026,
 * definida en el script TiendaGenerica.sql.
 *
 * La relacion con TipoDocumento se modela con @ManyToOne porque la tabla
 * declara la llave foranea fktipodocumento sobre la columna idTipoDocumento.
 */
@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "numeroDocumento", nullable = false, length = 40)
    private String numeroDocumento;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    /**
     * Contrasena cifrada con BCrypt. Nunca se expone en las respuestas de la API.
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "nombreUsuario", nullable = false, length = 255)
    private String nombreUsuario;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }
}
