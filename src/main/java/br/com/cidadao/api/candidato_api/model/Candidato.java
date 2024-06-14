package br.com.cidadao.api.candidato_api.model;

import br.com.cidadao.api.candidato_api.dto.CandidatoDTO;
import br.com.cidadao.api.candidato_api.dto.NovoCandidatoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "candidato")
public class Candidato implements Serializable {
    @Serial
    private static final long serialVersionUID = -1630090017922899571L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    @Size(min=4, max = 100)
    private String nome;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @Column(name = "data_criacao", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(nullable = false, length = 1)
    private String sexo = "M";

    @Column(nullable = false)
    private Integer nota;
    private String logradouro;
    private String bairro;
    private String cidade;

    @Column(length = 2)
    private String uf;

    public Candidato() {
    }

    public Candidato(Long id,
                     String nome,
                     Date nascimento,
                     Date dataCriacao,
                     String sexo,
                     Integer nota,
                     String logradouro,
                     String bairro,
                     String cidade,
                     String uf) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.dataCriacao = dataCriacao;
        this.sexo = sexo;
        this.nota = nota;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    //static factory
    public static Candidato fromCandidatoDto(CandidatoDTO candidatoDto) {
        return new Candidato(
                candidatoDto.getId(),
                candidatoDto.getNome(),
                candidatoDto.getNascimento(),
                candidatoDto.getDataCriacao(),
                candidatoDto.getSexo(),
                candidatoDto.getNota(),
                candidatoDto.getLogradouro(),
                candidatoDto.getBairro(),
                candidatoDto.getCidade(),
                candidatoDto.getUf()
        );
    }

    public static Candidato fromNovoCandidato(NovoCandidatoDTO novoCandidatoDTO) {
        return new Candidato(
                null,
                novoCandidatoDTO.getNome(),
                novoCandidatoDTO.getNascimento(),
                new Date(),
                novoCandidatoDTO.getSexo(),
                novoCandidatoDTO.getNota(),
                novoCandidatoDTO.getLogradouro(),
                novoCandidatoDTO.getBairro(),
                novoCandidatoDTO.getCidade(),
                novoCandidatoDTO.getUf().toUpperCase()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return Objects.equals(id, candidato.id) && Objects.equals(nome, candidato.nome) && Objects.equals(nascimento, candidato.nascimento) && Objects.equals(dataCriacao, candidato.dataCriacao) && Objects.equals(sexo, candidato.sexo) && Objects.equals(nota, candidato.nota) && Objects.equals(logradouro, candidato.logradouro) && Objects.equals(bairro, candidato.bairro) && Objects.equals(cidade, candidato.cidade) && Objects.equals(uf, candidato.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nascimento, dataCriacao, sexo, nota, logradouro, bairro, cidade, uf);
    }
}
