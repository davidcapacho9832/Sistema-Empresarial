package com.tienda.usuarios.excepcion;

/**
 * Se lanza cuando los datos enviados son sintacticamente validos pero
 * no tienen sentido para el negocio; por ejemplo, un idTipoDocumento
 * que no existe en el catalogo tipodocumento.
 * Es manejada por ResponseExceptionHandler y retorna HTTP 400.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
