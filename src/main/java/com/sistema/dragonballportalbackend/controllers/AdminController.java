package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.dto.DecisionRequest;
import com.sistema.dragonballportalbackend.logic.servicios.ContribucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private ContribucionService contribucionService;

    @GetMapping("/pendientes")
    public ResponseEntity<?> pendientes() {
        return ResponseEntity.ok(contribucionService.findPendientes());
    }

    @PostMapping("/contribuciones/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Integer id,
                                     @RequestBody DecisionRequest request) {
        String error = contribucionService.aprobar(id, request.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución aprobada");
    }

    @PostMapping("/contribuciones/{id}/rechazar")
    public ResponseEntity<?> rechazar(@PathVariable Integer id,
                                      @RequestBody DecisionRequest request) {
        String error = contribucionService.rechazar(id, request.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución rechazada");
    }
}
