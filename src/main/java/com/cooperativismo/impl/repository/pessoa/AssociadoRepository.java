package com.cooperativismo.impl.repository.pessoa;

import com.cooperativismo.impl.entity.pessoa.Associado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository  extends CrudRepository<Associado,Long> {

    Optional<Associado> findById(Long id);
    Associado findByCpf(String cpf);
}
