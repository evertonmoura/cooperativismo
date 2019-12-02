package com.cooperativismo.impl.repository;

import com.cooperativismo.impl.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Long> {

    Optional<Voto> findById(Long id);
    List<Voto> findByCpfAssociado(String cpf);


}
