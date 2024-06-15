package br.com.cidadao.api.candidato_api.repository;

import br.com.cidadao.api.candidato_api.domain.candidato.Candidato;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    @Query("SELECT c FROM candidato c WHERE (:nome IS NULL OR c.nome LIKE %:nome%) " +
            "AND (:nascimento IS NULL OR c.nascimento = :nascimento) " +
            "AND (:sexo IS NULL OR c.sexo = :sexo) " +
            "AND (:nota IS NULL OR c.nota = :nota)")
    List<Candidato> findByFilters(@Param("nome") String nome,
                                  @Param("nascimento") Date nascimento,
                                  @Param("sexo") String sexo,
                                  @Param("nota") Integer nota,
                                  Sort sort);

}
