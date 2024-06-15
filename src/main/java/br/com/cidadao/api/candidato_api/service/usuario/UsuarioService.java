package br.com.cidadao.api.candidato_api.service.usuario;

import br.com.cidadao.api.candidato_api.domain.user.Usuario;
import br.com.cidadao.api.candidato_api.dto.usuario.UsuarioDTO;
import br.com.cidadao.api.candidato_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario criar(UsuarioDTO usuarioDto) {
        Usuario usuario = Usuario.fromUsuarioDto(usuarioDto);
        String passwordEncoded = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncoded);

        return usuarioRepository.save(usuario);
    }
}
