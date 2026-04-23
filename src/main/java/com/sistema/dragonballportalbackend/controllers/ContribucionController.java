package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.dto.ContribucionRequest;
import com.sistema.dragonballportalbackend.logic.model.Contribucion;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import com.sistema.dragonballportalbackend.logic.servicios.ContribucionService;
import com.sistema.dragonballportalbackend.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contribuciones")
@CrossOrigin
public class ContribucionController {
    @Autowired
    private ContribucionService contribucionService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ContribucionRequest request,
                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        Contribucion c = new Contribucion();
        c.setTipo(request.getTipo());
        c.setTitulo(request.getTitulo());
        c.setContenidoHtml(request.getContenidoHtml());

        Usuario usuario = new Usuario();
        usuario.setId(userDetails.getId());
        c.setUsuario(usuario);

        String error = contribucionService.crearContribucion(c);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución enviada para revisión");
    }

    @GetMapping("/mias")
    public ResponseEntity<?> mias(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(contribucionService.findByUsuarioId(userDetails.getId()));
    }
}
