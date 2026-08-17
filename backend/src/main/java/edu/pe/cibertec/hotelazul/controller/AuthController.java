package edu.pe.cibertec.hotelazul.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pe.cibertec.hotelazul.dto.LoginRequest;
import edu.pe.cibertec.hotelazul.dto.LoginResponse;
import edu.pe.cibertec.hotelazul.entity.Usuario;
import edu.pe.cibertec.hotelazul.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.autenticar(request.getUsername(), request.getPassword());
        if (usuario == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
        LoginResponse response = new LoginResponse(usuario.getId(), usuario.getUsername(), usuario.getRol());
        return ResponseEntity.ok(response);
    }

}
