package com.cooperativismo.impl.repository;

import com.cooperativismo.impl.entity.Sessao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao,Long> {

    Optional<Sessao> findById(Long id);
    Sessao findByIdPauta(Long idPauta);
}
