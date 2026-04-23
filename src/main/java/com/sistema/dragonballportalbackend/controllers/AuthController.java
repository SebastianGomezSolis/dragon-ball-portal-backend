package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.dto.AuthRequest;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import com.sistema.dragonballportalbackend.logic.servicios.AuthService;
import com.sistema.dragonballportalbackend.logic.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        String error = usuarioService.registrar(usuario);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok("Sesión cerrada");
    }
}
