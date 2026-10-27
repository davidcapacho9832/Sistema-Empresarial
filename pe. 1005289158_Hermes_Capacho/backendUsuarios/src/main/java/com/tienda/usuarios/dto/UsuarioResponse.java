package com.tienda.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representacion del usuario que devuelve la API.
 * No contiene la contrasena, ni siquiera cifrada.
 */
@Schema(description = "Usuario devuelto por la API")
public class UsuarioResponse {

    private Integer id;
    private Integer idTipoDocumento;
    @Schema(description = "Descripcion del tipo de documento", example = "CEDULA")
    private String tipoDocumento;
    private String numeroDocumento;
    private String email;
    private String nombre;
    private String nombreUsuario;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Integer id, Integer idTipoDocumento, String tipoDocumento,
                            String numeroDocumento, String email, String nombre, String nombreUsuario) {
        this.id = id;
        this.idTipoDocumento = idTipoDocumento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
