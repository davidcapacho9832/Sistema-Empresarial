## 🚀 Características y Módulos (CRUD)
El sistema cuenta con una API RESTful completa y funcional, conectada a un Frontend que consume los servicios mediante AJAX/Fetch API:

1. **Gestión de Usuarios:** 
   - Registro y consulta de usuarios.
   - Validaciones estrictas (email, documento único, etc.).
   - Contraseñas encriptadas con BCrypt.
2. **Gestión de Clientes:** 
   - Registro, listado de clientes y relación con tipos de documento.
3. **Gestión de Servicios:** 
   - Administración de productos/servicios (precios, IVA).
4. **Módulo de Login:** 
   - Autenticación de usuarios validando las credenciales encriptadas contra la base de datos.
5. **Manejo de Excepciones:** 
   - Controlador global (`@ControllerAdvice`) que retorna respuestas JSON limpias (400, 404, 409, 500) sin exponer errores internos de SQL.

## 🗄️ Diseño de la Base de Datos
El diseño relacional de la base de datos (tablas `usuario`, `cliente`, `producto`, `tipodocumento`, etc.) se encuentra documentado visualmente en el archivo **`Base de datos.png`** incluido en este repositorio.

## 📸 Evidencias y Presentación
- **Video de presentación del proyecto:** Se encuentra en la carpeta `Video Proyecto/`.
- **Colección de Postman:** Se incluye la carpeta `postman` para probar las peticiones a la API.

## ⚙️ Cómo ejecutar el proyecto localmente
Si deseas probar este proyecto en tu computadora, sigue estos pasos:

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/davidcapacho9832/Sistema-Empresarial.git
