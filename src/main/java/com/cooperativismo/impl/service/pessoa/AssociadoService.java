package com.cooperativismo.impl.service.pessoa;

import com.cooperativismo.impl.converters.pessoa.AssociadoConverter;
import com.cooperativismo.impl.dto.pessoa.AssociadoDTO;
import com.cooperativismo.impl.entity.pessoa.Associado;
import com.cooperativismo.impl.repository.pessoa.AssociadoRepository;
import com.cooperativismo.impl.validator.pessoa.AssociadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class AssociadoService {

    private AssociadoRepository associadoRepository;
    private AssociadoValidator associadoValidator;
    private AssociadoConverter associadoConverter;


    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository,AssociadoValidator associadoValidator,AssociadoConverter associadoConverter){
        this.associadoRepository=associadoRepository;
        this.associadoValidator=associadoValidator;
        this.associadoConverter=associadoConverter;
    }

    public AssociadoDTO save(String nome, String cpf) throws ValidationException {
        Associado associado = new Associado();
        associado.setNome(nome);
        associado.setCpf(cpf);
        associadoValidator.validateAssociado(associado);
        return associadoConverter.toDTO(associadoRepository.save(associado));
    }

}
