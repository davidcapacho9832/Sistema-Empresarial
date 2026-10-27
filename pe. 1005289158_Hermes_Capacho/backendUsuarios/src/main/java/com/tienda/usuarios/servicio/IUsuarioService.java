package com.tienda.usuarios.servicio;

import java.util.List;
import com.tienda.usuarios.dto.LoginDto;
import com.tienda.usuarios.dto.UsuarioRequest;
import com.tienda.usuarios.dto.UsuarioResponse;
import com.tienda.usuarios.dto.UsuarioUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {
    
    List<UsuarioResponse> findAll();
    UsuarioResponse findById(Integer id);
    UsuarioResponse create(UsuarioRequest request);
    UsuarioResponse update(Integer id, UsuarioUpdateRequest request);
    void cambiarPassword(Integer id, String password);
    void delete(Integer id);

    // --- ESTOS DOS SON LOS DEL LOGIN ---
    int login(LoginDto loginDto);
    ResponseEntity<?> ingresar(LoginDto loginDto);
}