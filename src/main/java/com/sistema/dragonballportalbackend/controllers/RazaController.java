package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.logic.servicios.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/razas")
@CrossOrigin
public class RazaController {
    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(razaService.buscarPorNombre(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        return ResponseEntity.ok(razaService.findById(id));
    }
}
