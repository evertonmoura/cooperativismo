package com.cooperativismo.impl.repository;

import com.cooperativismo.impl.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {

    Optional<Pauta> findById(Long id);

}
