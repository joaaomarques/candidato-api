package br.com.cidadao.api.candidato_api.dto.candidato;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class NovoCandidatoDTO {

    @Size(min=4, max = 100)
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date nascimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataCriacao;
    @Size(max = 1)
    private String sexo = "M";
    private Integer nota;
    private String logradouro;
    private String bairro;
    private String cidade;

    private String uf;

    public NovoCandidatoDTO() {
    }

    public NovoCandidatoDTO(String nome,
                            Date nascimento,
                            Date dataCriacao,
                            String sexo,
                            Integer nota,
                            String logradouro,
                            String bairro,
                            String cidade,
                            String uf) {
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
}
