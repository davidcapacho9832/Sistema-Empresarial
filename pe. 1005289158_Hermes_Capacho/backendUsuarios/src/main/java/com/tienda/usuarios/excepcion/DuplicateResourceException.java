package com.tienda.usuarios.excepcion;

/**
 * Se lanza cuando se intenta crear o actualizar un Usuario con un numero
 * de documento, email o nombre de usuario que ya pertenece a otro registro.
 * Es manejada por ResponseExceptionHandler y retorna HTTP 409 (Conflict).
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String mensaje) {
        super(mensaje);
    }
}
