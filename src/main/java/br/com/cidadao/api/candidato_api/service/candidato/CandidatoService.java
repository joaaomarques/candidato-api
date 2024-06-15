package br.com.cidadao.api.candidato_api.service.candidato;

import br.com.cidadao.api.candidato_api.dto.candidato.CandidatoDTO;
import br.com.cidadao.api.candidato_api.dto.candidato.NovoCandidatoDTO;
import br.com.cidadao.api.candidato_api.exceptions.ObjetoNaoEncontradoException;
import br.com.cidadao.api.candidato_api.domain.candidato.Candidato;
import br.com.cidadao.api.candidato_api.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato find(Long id) {

            return candidatoRepository.findById(id)
                    .orElseThrow(() -> new ObjetoNaoEncontradoException("Candidato não encontrado com ID: " + id));

    }

    public Collection<CandidatoDTO> findAll() {

        List<Candidato> candidatos = this.candidatoRepository.findAll();

        if(candidatos.isEmpty()) {
            return Collections.emptyList();
        }

        return candidatos.stream().map(CandidatoDTO::fromCandidato).collect(Collectors.toList());
    }

    public Collection<CandidatoDTO> findByFilters(String nome, Date nascimento, String sexo, Integer nota, Sort sort) {

        List<Candidato> candidatos = this.candidatoRepository.findByFilters(nome,nascimento,sexo,nota,sort);

        if(candidatos.isEmpty()) {
            return Collections.emptyList();
        }

        return candidatos.stream().map(CandidatoDTO::fromCandidato).collect(Collectors.toList());
    }

    public Candidato create(NovoCandidatoDTO novoCandidatoDTO) {

        Candidato candidato = Candidato.fromNovoCandidato(novoCandidatoDTO);
        return this.candidatoRepository.save(candidato);
    }

    public Candidato update(CandidatoDTO candidatoDto) {
        Candidato candidatoExistente = find(candidatoDto.getId());
        Candidato candidatoAtualizacao = Candidato.fromCandidatoDto(candidatoDto);
        updateData(candidatoAtualizacao, candidatoExistente);
        return this.candidatoRepository.save(candidatoExistente);
    }

    public static void updateData(Candidato candidatoAtualizacao, Candidato candidatoExistente) {
        candidatoExistente.setNome(candidatoAtualizacao.getNome());
        candidatoExistente.setNascimento(candidatoAtualizacao.getNascimento());
        candidatoExistente.setSexo(candidatoAtualizacao.getSexo());
        candidatoExistente.setNota(candidatoAtualizacao.getNota());
        candidatoExistente.setLogradouro(candidatoAtualizacao.getLogradouro());
        candidatoExistente.setBairro(candidatoAtualizacao.getBairro());
        candidatoExistente.setCidade(candidatoAtualizacao.getCidade());
        candidatoExistente.setUf(candidatoAtualizacao.getUf());
    }

    public void delete(Long id) {
        Candidato candidato = find(id);
        this.candidatoRepository.delete(candidato);
    }
}
