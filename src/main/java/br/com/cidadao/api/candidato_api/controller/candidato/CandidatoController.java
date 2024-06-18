package br.com.cidadao.api.candidato_api.controller.candidato;

import br.com.cidadao.api.candidato_api.dto.candidato.CandidatoDTO;
import br.com.cidadao.api.candidato_api.dto.candidato.NovoCandidatoDTO;
import br.com.cidadao.api.candidato_api.domain.candidato.Candidato;
import br.com.cidadao.api.candidato_api.service.candidato.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("api/v1/candidato")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping("/lista")
    public ResponseEntity<Collection<CandidatoDTO>> findAll() {
        Collection<CandidatoDTO> candidatoDTOS = this.candidatoService.findAll();
        return ResponseEntity.ok().body(candidatoDTOS);
    }

    @GetMapping("/lista/filter")
    public ResponseEntity<Collection<CandidatoDTO>> findByFilters(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date nascimento,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) Integer nota,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, sortBy);

        Collection<CandidatoDTO> candidatoDTOS = this.candidatoService.findByFilters(nome, nascimento, sexo, nota, sort);

        if(candidatoDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(candidatoDTOS);

    }


    @GetMapping("/{id}")
    public ResponseEntity<CandidatoDTO> findById(@PathVariable Long id) {
        Candidato candidato = this.candidatoService.find(id);
        return ResponseEntity.ok().body(CandidatoDTO.fromCandidato(candidato));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NovoCandidatoDTO novoCandidatoDTO) {

        Candidato candidato = this.candidatoService.create(novoCandidatoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidato.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatoDTO> update(@Valid @RequestBody CandidatoDTO candidatoDTO, @PathVariable Long id) {
        candidatoDTO.setId(id);
        this.candidatoService.update(candidatoDTO);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
