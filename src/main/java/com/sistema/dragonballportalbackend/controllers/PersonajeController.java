package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.logic.servicios.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personajes")
@CrossOrigin
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(personajeService.buscarPorNombre(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        return ResponseEntity.ok(personajeService.findById(id));
    }
}
