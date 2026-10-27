package com.tienda.usuarios.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.usuarios.dto.LoginDto;
import com.tienda.usuarios.dto.PasswordRequest;
import com.tienda.usuarios.dto.UsuarioRequest;
import com.tienda.usuarios.dto.UsuarioResponse;
import com.tienda.usuarios.dto.UsuarioUpdateRequest;
import com.tienda.usuarios.excepcion.ExceptionResponse;
import com.tienda.usuarios.servicio.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controlador REST que expone las operaciones CRUD sobre la tabla Usuario
 */


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre los usuarios de la tienda")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Obtiene el listado completo de usuarios")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(summary = "Obtiene un usuario por su id")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(
            @Parameter(description = "Identificador del usuario", example = "1")
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(summary = "Registra un nuevo usuario")
    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse creado = usuarioService.create(request);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza la informacion de un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UsuarioUpdateRequest request) {
        return ResponseEntity.ok(usuarioService.update(id, request));
    }

    @Operation(summary = "Cambia la contrasena de un usuario")
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> cambiarPassword(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PasswordRequest request) {
        usuarioService.cambiarPassword(id, request.getPassword());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Elimina un usuario por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // ENDPOINTS DE LOGIN (Agregados correctamente dentro de la clase)
    // =========================================================

    @Operation(summary = "Valida si el usuario y contraseña coinciden (Retorna 1 o 0)")
    @PostMapping("/loginclient")
    public int login(@Valid @RequestBody LoginDto usuario) {
        return usuarioService.login(usuario);
    }

    @Operation(summary = "Ingresa al sistema y devuelve los datos del usuario")
    @PostMapping("/login")
    public ResponseEntity<?> loginCliente(@Valid @RequestBody LoginDto usuario) {
        return usuarioService.ingresar(usuario);
    }
}