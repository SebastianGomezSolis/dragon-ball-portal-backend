package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.logic.servicios.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sagas")
@CrossOrigin
public class SagaController {
    @Autowired
    private SagaService sagaService;

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(sagaService.buscarPorNombre(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        return ResponseEntity.ok(sagaService.findById(id));
    }
}
