package br.com.cidadao.api.candidato_api.domain.user;

import br.com.cidadao.api.candidato_api.dto.usuario.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String username;
    @JsonIgnore
    @Column(name = "SENHA")
    private String password;

    public Usuario() {
    }

    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Usuario fromUsuarioDto(UsuarioDTO usuarioDTO) {

        return new Usuario(
                usuarioDTO.getUsername(),
                usuarioDTO.getPassword()
        );
    }
}
