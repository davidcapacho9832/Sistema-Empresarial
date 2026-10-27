# API REST - Usuarios TiendaGenerica

Microservicio CRUD sobre la tabla `usuario` de la base de datos
`tiendagenericaDAEII2026`, desarrollado con Spring Boot, Maven y JPA para la
asignatura Desarrollo de Aplicaciones Empresariales.

**Estudiante:** Hermes David Capacho

## Tecnologias

| Componente | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.4 |
| Spring Data JPA (Hibernate) | incluida en Boot |
| Bean Validation (Hibernate Validator) | incluida en Boot |
| springdoc-openapi (Swagger UI) | 2.6.0 |
| MySQL Connector/J | incluida en Boot |
| spring-security-crypto (BCrypt) | incluida en Boot |

## Puesta en marcha

1. Ejecutar el script `TiendaGenerica.sql` en MySQL Workbench si la base de
   datos aun no existe.
2. Definir la clave de la base de datos como variable de entorno:

   ```bash
   # Windows (PowerShell)
   $env:DB_PASSWORD="tu_clave"
   # Linux / macOS
   export DB_PASSWORD="tu_clave"
   ```

   Si prefiere no usar variables de entorno, reemplace `CAMBIA_ESTA_CLAVE`
   en `src/main/resources/application.properties`.
3. Ejecutar la aplicacion:

   ```bash
   mvn spring-boot:run
   ```

## Endpoints

Base: `http://localhost:8096/api/usuarios`

| Metodo | Ruta | Descripcion | Exito | Errores |
|---|---|---|---|---|
| GET | `/api/usuarios` | Lista todos los usuarios | 200 | - |
| GET | `/api/usuarios/{id}` | Consulta un usuario | 200 | 404 |
| POST | `/api/usuarios` | Registra un usuario | 201 | 400, 409 |
| PUT | `/api/usuarios/{id}` | Actualiza un usuario | 200 | 400, 404, 409 |
| PUT | `/api/usuarios/{id}/password` | Cambia la contrasena | 204 | 400, 404 |
| DELETE | `/api/usuarios/{id}` | Elimina un usuario | 204 | 404, 409 |

Documentacion Swagger: `http://localhost:8096/swagger-ui/index.html`

## Decisiones de diseno

- **DTO en lugar de la entidad.** La API recibe `UsuarioRequest` /
  `UsuarioUpdateRequest` y devuelve `UsuarioResponse`. Asi el cliente no puede
  enviar el `id` y la contrasena nunca sale en una respuesta.
- **Contrasena cifrada con BCrypt.** La columna `password` no vuelve a
  almacenar texto plano. El cambio de contrasena tiene su propio endpoint.
- **Relacion `@ManyToOne` con `TipoDocumento`.** La tabla declara la llave
  foranea `fktipodocumento`, y modelarla permite devolver la descripcion
  (`CEDULA`, `TARJETA`, `PASAPORTE`) y validar el id antes de guardar.
- **Manejo centralizado de excepciones** con `@ControllerAdvice`: 400 para
  validaciones, 404 para recurso inexistente, 409 para duplicados e integridad
  referencial, 500 generico sin exponer detalles internos.
- **Credenciales fuera del codigo**, tomadas de variables de entorno.

## Estructura

```
src/main/java/com/tienda/usuarios
  BackendUsuariosApplication.java
  config/        OpenApiConfig, SeguridadConfig
  controlador/   UsuarioController
  dto/           UsuarioRequest, UsuarioUpdateRequest, PasswordRequest, UsuarioResponse
  excepcion/     ResourceNotFoundException, DuplicateResourceException,
                 BadRequestException, ExceptionResponse, ResponseExceptionHandler
  modelo/        Usuario, TipoDocumento
  repositorio/   IUsuarioRepository, ITipoDocumentoRepository
  servicio/      IUsuarioService, UsuarioService
postman/         coleccion lista para importar
sql/             restricciones UNIQUE recomendadas
```
