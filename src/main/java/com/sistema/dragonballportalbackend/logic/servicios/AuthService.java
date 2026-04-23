package com.sistema.dragonballportalbackend.logic.servicios;

import com.sistema.dragonballportalbackend.dto.AuthRequest;
import com.sistema.dragonballportalbackend.dto.AuthResponse;
import com.sistema.dragonballportalbackend.logic.model.SesionUsuarioBean;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SesionUsuarioBean sesionUsuarioBean;

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioService.findByUsername(request.getUsername());

        sesionUsuarioBean.login(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                Boolean.TRUE.equals(usuario.getActivo())
        );

        String token = jwtService.generateToken(usuario.getUsername());
        return new AuthResponse(token, usuario.getUsername(), usuario.getRol().name());
    }

    public void logout() {
        sesionUsuarioBean.logout();
    }
}
