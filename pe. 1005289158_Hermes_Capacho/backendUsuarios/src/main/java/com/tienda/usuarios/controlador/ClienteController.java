package com.tienda.usuarios.controlador;

import com.tienda.usuarios.modelo.Cliente;
import com.tienda.usuarios.modelo.TipoDocumento;
import com.tienda.usuarios.repositorio.IClienteRepository;
import com.tienda.usuarios.repositorio.ITipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteRepository clienteRepo;

    @Autowired
    private ITipoDocumentoRepository tipoDocRepo;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> payload) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(payload.get("nombre").toString());
            cliente.setEmail(payload.get("email").toString());
            cliente.setNumeroDocumento(payload.get("numeroDocumento").toString());
            cliente.setDireccion(payload.get("direccion").toString());
            cliente.setTelefono(payload.get("telefono").toString());

            // Buscar el tipo de documento de forma segura
            Integer idTipoDoc = Integer.parseInt(payload.get("idTipoDocumento").toString());
            TipoDocumento tipoDoc = tipoDocRepo.findById(idTipoDoc)
                .orElseThrow(() -> new RuntimeException("Tipo de documento no existe"));
            
            cliente.setTipoDocumento(tipoDoc);
            
            return ResponseEntity.ok(clienteRepo.save(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar: " + e.getMessage());
        }
    }
}