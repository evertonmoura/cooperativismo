package com.cooperativismo.impl.repository;

import com.cooperativismo.impl.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao,Long> {

    Optional<Sessao> findById(Long id);
    Sessao findByIdPauta(Long idPauta);
}
