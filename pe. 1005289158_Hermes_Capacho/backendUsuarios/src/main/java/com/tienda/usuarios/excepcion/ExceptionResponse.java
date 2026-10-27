package com.tienda.usuarios.excepcion;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Estructura estandar de la informacion que se muestra al consumidor de la API
 * cuando ocurre un error o una excepcion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Date timestamp;
    private int estado;
    private String mensaje;
    private String detalles;
    private Map<String, String> erroresValidacion;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestamp, int estado, String mensaje, String detalles) {
        this.timestamp = timestamp;
        this.estado = estado;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public ExceptionResponse(Date timestamp, int estado, String mensaje, String detalles,
                              Map<String, String> erroresValidacion) {
        this(timestamp, estado, mensaje, detalles);
        this.erroresValidacion = erroresValidacion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Map<String, String> getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(Map<String, String> erroresValidacion) {
        this.erroresValidacion = erroresValidacion;
    }
}
