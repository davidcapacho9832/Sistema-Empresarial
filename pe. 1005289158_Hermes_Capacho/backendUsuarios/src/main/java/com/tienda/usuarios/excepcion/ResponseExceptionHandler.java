package com.tienda.usuarios.excepcion;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manejador global de excepciones de la API.
 * Centraliza la forma en que se presentan los errores al consumidor del
 * servicio (Postman, Swagger, frontend, etc.) y evita que detalles internos
 * como sentencias SQL lleguen al cliente: esos quedan solo en el log.
 */
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    // Usuario / recurso no encontrado -> 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> manejarRecursoNoEncontrado(ResourceNotFoundException e) {
        logger.warn("Recurso no encontrado: {}", e.getMessage());
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                e.getMessage()
        );
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // Documento, email o nombre de usuario duplicado -> 409
    @ExceptionHandler(DuplicateResourceException.class)
    public final ResponseEntity<ExceptionResponse> manejarRecursoDuplicado(DuplicateResourceException e) {
        logger.warn("Conflicto de datos duplicados: {}", e.getMessage());
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.CONFLICT.value(),
                "Conflicto con datos existentes",
                e.getMessage()
        );
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    // Datos con formato valido pero sin sentido de negocio -> 400
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> manejarPeticionInvalida(BadRequestException e) {
        logger.warn("Peticion invalida: {}", e.getMessage());
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Datos invalidos",
                e.getMessage()
        );
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // Violacion de integridad referencial o de unicidad en la base de datos -> 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionResponse> manejarIntegridad(DataIntegrityViolationException e) {
        logger.warn("Violacion de integridad de datos: {}", e.getMostSpecificCause().getMessage());
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.CONFLICT.value(),
                "Conflicto de integridad de datos",
                "La operacion no se puede completar porque el registro esta relacionado con otros "
                        + "datos (por ejemplo ventas asociadas) o porque referencia un valor que no existe."
        );
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    // El id enviado en la URL no es un numero -> 400
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ExceptionResponse> manejarTipoIncorrecto(MethodArgumentTypeMismatchException e) {
        logger.warn("Parametro con tipo incorrecto: {}", e.getMessage());
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Parametro invalido",
                "El valor enviado para '" + e.getName() + "' no tiene el tipo esperado."
        );
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // Cualquier otra excepcion no controlada -> 500 (sin exponer el detalle interno)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception e) {
        logger.error("Error inesperado: {}", e.getMessage(), e);
        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocurrio un error inesperado",
                "Consulte el log del servidor para obtener mas informacion."
        );
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Errores de validacion de @Valid en el cuerpo de la peticion -> 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers,
                                                                    HttpStatusCode status,
                                                                    WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });

        logger.warn("Error de validacion: {}", errores);

        ExceptionResponse respuesta = new ExceptionResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Error de validacion en los datos enviados",
                "Revise los campos indicados en erroresValidacion.",
                errores
        );
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}
