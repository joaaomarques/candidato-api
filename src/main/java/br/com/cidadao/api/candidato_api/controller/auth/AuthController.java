package br.com.cidadao.api.candidato_api.controller.auth;

import br.com.cidadao.api.candidato_api.dto.auth.AuthRequestDTO;
import br.com.cidadao.api.candidato_api.dto.auth.JwtResponseDTO;
import br.com.cidadao.api.candidato_api.service.jwt.JwtService;
import br.com.cidadao.api.candidato_api.service.jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return new JwtResponseDTO(jwtService.GenerateToken(authRequestDTO.getUsername()));
        } else {
            throw new UsernameNotFoundException("Usuário inválido!");
        }
    }
}
