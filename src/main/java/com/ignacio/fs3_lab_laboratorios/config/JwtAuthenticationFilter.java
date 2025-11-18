package com.ignacio.fs3_lab_laboratorios.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        System.out.println("=== Filtro JWT ejecutándose ===");
        String authHeader = request.getHeader("Authorization");
        System.out.println("Header Authorization: " + (authHeader != null ? "Presente" : "Ausente"));
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token no proporcionado o inválido\"}");
            return;
        }
        
        String token = authHeader.replace("Bearer ", "");
        System.out.println("Token recibido (primeros 20 chars): " + token.substring(0, Math.min(20, token.length())));
        
        if (!jwtUtil.isTokenValid(token)) {
            System.err.println("Token NO válido");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token inválido o expirado\"}");
            return;
        }
        
        System.out.println("Token VÁLIDO - permitiendo acceso");
        
        // Agregar información del usuario al request para uso posterior
        request.setAttribute("userId", jwtUtil.extractUserId(token));
        request.setAttribute("email", jwtUtil.extractEmail(token));
        request.setAttribute("rolId", jwtUtil.extractRolId(token));
        request.setAttribute("rolNombre", jwtUtil.extractRolNombre(token));
        
        filterChain.doFilter(request, response);
    }
}
