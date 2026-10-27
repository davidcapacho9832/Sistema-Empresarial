package com.tienda.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Datos que recibe la API para actualizar un usuario (PUT).
 * No incluye la contrasena: esa se cambia por el endpoint dedicado
 * PUT /api/usuarios/{id}/password, para no exponerla en cada actualizacion.
 */
@Schema(description = "Datos actualizables de un usuario (sin contrasena)")
public class UsuarioUpdateRequest {

    @NotNull(message = "El tipo de documento es obligatorio")
    @Schema(description = "Id del tipo de documento (5=CEDULA, 6=TARJETA, 7=PASAPORTE)", example = "5")
    private Integer idTipoDocumento;

    @NotBlank(message = "El numero de documento es obligatorio")
    @Size(max = 40, message = "El numero de documento no debe superar los 40 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El numero de documento solo puede contener digitos")
    @Schema(example = "1098765432")
    private String numeroDocumento;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email enviado no tiene un formato valido")
    @Size(max = 255, message = "El email no debe superar los 255 caracteres")
    @Schema(example = "hermes.capacho@example.com")
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    @Schema(example = "Hermes David Capacho Rincon")
    private String nombre;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$",
            message = "El nombre de usuario solo puede contener letras, numeros y los simbolos . _ -")
    @Schema(example = "hcapacho")
    private String nombreUsuario;

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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
