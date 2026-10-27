package com.tienda.usuarios.excepcion;

/**
 * Se lanza cuando se busca, actualiza o elimina un Usuario
 * cuyo id no existe en la base de datos.
 * Es manejada por ResponseExceptionHandler y retorna HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
