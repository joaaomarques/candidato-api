package br.com.cidadao.api.candidato_api.controller.usuario;

import br.com.cidadao.api.candidato_api.domain.user.Usuario;
import br.com.cidadao.api.candidato_api.dto.usuario.UsuarioDTO;
import br.com.cidadao.api.candidato_api.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = this.usuarioService.criar(usuarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
