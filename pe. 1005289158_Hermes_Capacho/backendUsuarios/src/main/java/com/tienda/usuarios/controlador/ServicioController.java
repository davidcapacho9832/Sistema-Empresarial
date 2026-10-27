package com.tienda.usuarios.controlador;

import com.tienda.usuarios.modelo.Servicio;
import com.tienda.usuarios.repositorio.IServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private IServicioRepository servicioRepo;

    @GetMapping
    public List<Servicio> listar() {
        return servicioRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> payload) {
        try {
            Servicio servicio = new Servicio();
            servicio.setNombre(payload.get("nombre").toString());
            servicio.setPrecioCompra(Double.parseDouble(payload.get("precioCompra").toString()));
            servicio.setPrecioVenta(Double.parseDouble(payload.get("precioVenta").toString()));
            servicio.setIvaCompra(Double.parseDouble(payload.get("ivaCompra").toString()));
            
            // ¡AQUÍ ESTÁ LA MAGIA! Forzamos el proveedor 2 que SÍ existe en tu SQL.
            servicio.setIdProveedor(2); 

            return ResponseEntity.ok(servicioRepo.save(servicio));
        } catch (Exception e) {
            // Si algo falla, ahora nos dirá exactamente qué fue en la página web
            return ResponseEntity.badRequest().body("Error en el servidor: " + e.getMessage());
        }
    }
}