package com.cooperativismo.impl.service.pessoa;

import com.cooperativismo.impl.converters.pessoa.AssociadoConverter;
import com.cooperativismo.impl.dto.pessoa.AssociadoDTO;
import com.cooperativismo.impl.entity.pessoa.Associado;
import com.cooperativismo.impl.exception.ValidationException;
import com.cooperativismo.impl.repository.pessoa.AssociadoRepository;
import com.cooperativismo.impl.validator.pessoa.AssociadoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssociadoService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AssociadoService.class);


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
        LOGGER.info("Salvando associado." + nome + " : " + cpf);
        Associado associado = criarAssociado(nome,cpf);
        associadoValidator.validateAssociado(associado);
        AssociadoDTO associadoDTO  = associadoConverter.toDTO(associadoRepository.save(associado));
        LOGGER.info("Salvando associado. OK " + associado.toString());
        return associadoDTO;
    }

    public Associado buscarAssociadoPorCpf(String cpf){
        return associadoRepository.findByCpf(cpf);
    }

    private Associado criarAssociado(String nome, String cpf){
        LOGGER.info("criarAssociado associado." + nome + " : " + cpf);
        Associado associado = new Associado();
        associado.setNome(nome);
        associado.setCpf(cpf);
        LOGGER.info("criarAssociado. OK " + associado.toString());
        return associado;
    }
}
