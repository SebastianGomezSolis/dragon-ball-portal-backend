package com.sistema.dragonballportalbackend.config;

import com.sistema.dragonballportalbackend.logic.model.Rol;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import com.sistema.dragonballportalbackend.logic.servicios.UsuarioService;
import com.sistema.dragonballportalbackend.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        // Verifica si ya existe un admin
        if (!usuarioRepository.existsByUsername("admin")) {

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("1234"); // ⚠️ IMPORTANTE: en texto plano aquí
            admin.setRol(Rol.ADMIN);

            // Usa el service para que haga el hash correctamente
            usuarioService.registrar(admin);

            System.out.println("✔ Admin creado automáticamente");
        } else {
            System.out.println("✔ Admin ya existe");
        }
    }
}