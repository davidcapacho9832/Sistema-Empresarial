package com.tienda.usuarios.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.usuarios.dto.LoginDto;
import com.tienda.usuarios.dto.UsuarioRequest;
import com.tienda.usuarios.dto.UsuarioResponse;
import com.tienda.usuarios.dto.UsuarioUpdateRequest;
import com.tienda.usuarios.excepcion.BadRequestException;
import com.tienda.usuarios.excepcion.DuplicateResourceException;
import com.tienda.usuarios.excepcion.ResourceNotFoundException;
import com.tienda.usuarios.modelo.TipoDocumento;
import com.tienda.usuarios.modelo.Usuario;
import com.tienda.usuarios.repositorio.ITipoDocumentoRepository;
import com.tienda.usuarios.repositorio.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private ITipoDocumentoRepository tipoDocumentoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UsuarioResponse> findAll() {
        return usuarioRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public UsuarioResponse findById(Integer id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return mapToResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        if (usuarioRepo.existsByNumeroDocumento(request.getNumeroDocumento()) ||
            usuarioRepo.existsByEmail(request.getEmail()) ||
            usuarioRepo.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new DuplicateResourceException("El documento, email o nombre de usuario ya existe.");
        }

        TipoDocumento tipoDoc = tipoDocumentoRepo.findById(request.getIdTipoDocumento())
                .orElseThrow(() -> new BadRequestException("Tipo de documento no válido."));

        Usuario usuario = new Usuario();
        usuario.setTipoDocumento(tipoDoc);
        usuario.setNumeroDocumento(request.getNumeroDocumento());
        usuario.setEmail(request.getEmail());
        usuario.setNombre(request.getNombre());
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptar

        return mapToResponse(usuarioRepo.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponse update(Integer id, UsuarioUpdateRequest request) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        TipoDocumento tipoDoc = tipoDocumentoRepo.findById(request.getIdTipoDocumento())
                .orElseThrow(() -> new BadRequestException("Tipo de documento no válido."));

        // Validaciones de duplicados (excluyendo el usuario actual)
        Optional<Usuario> porDoc = usuarioRepo.findByNumeroDocumento(request.getNumeroDocumento());
        if (porDoc.isPresent() && !porDoc.get().getId().equals(id)) {
            throw new DuplicateResourceException("El número de documento ya está en uso.");
        }
        Optional<Usuario> porUser = usuarioRepo.findByNombreUsuario(request.getNombreUsuario());
        if (porUser.isPresent() && !porUser.get().getId().equals(id)) {
            throw new DuplicateResourceException("El nombre de usuario ya está en uso.");
        }

        usuario.setTipoDocumento(tipoDoc);
        usuario.setNumeroDocumento(request.getNumeroDocumento());
        usuario.setEmail(request.getEmail());
        usuario.setNombre(request.getNombre());
        usuario.setNombreUsuario(request.getNombreUsuario());

        return mapToResponse(usuarioRepo.save(usuario));
    }

    @Override
    @Transactional
    public void cambiarPassword(Integer id, String password) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuario.setPassword(passwordEncoder.encode(password));
        usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!usuarioRepo.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepo.deleteById(id);
    }

    // ==========================================
    // MÉTODOS DE LOGIN
    // ==========================================

    @Override
    public int login(LoginDto loginDto) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByNombreUsuario(loginDto.getNombreUsuario());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword())) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public ResponseEntity<?> ingresar(LoginDto loginDto) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByNombreUsuario(loginDto.getNombreUsuario());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword())) {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("Mensaje", "Datos correctos");
                respuesta.put("Usuario", usuario.getNombre());
                respuesta.put("nombreUsuario", usuario.getNombreUsuario());
                return ResponseEntity.ok(respuesta);
            }
        }
        Map<String, Object> error = new HashMap<>();
        error.put("Mensaje", "Alerta: Usuario o Password incorrectos");
        error.put("Usuario", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Método auxiliar para convertir la Entidad a DTO de Respuesta
        // Método auxiliar para convertir la Entidad a DTO de Respuesta
    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setNumeroDocumento(usuario.getNumeroDocumento());
        response.setNombreUsuario(usuario.getNombreUsuario());
        response.setEmail(usuario.getEmail());
        
        // Mapeo del tipo de documento
        if (usuario.getTipoDocumento() != null) {
            response.setIdTipoDocumento(usuario.getTipoDocumento().getId());
            response.setTipoDocumento(usuario.getTipoDocumento().getTipo());
        }
        
        return response;
    }
}