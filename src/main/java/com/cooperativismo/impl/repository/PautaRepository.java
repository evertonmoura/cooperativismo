package com.cooperativismo.impl.repository;

import com.cooperativismo.impl.entity.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends CrudRepository<Pauta,Long> {

    Optional<Pauta> findById(Long id);

}
