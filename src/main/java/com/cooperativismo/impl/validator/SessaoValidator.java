package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Sessao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;


@Component
public class SessaoValidator {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SessaoValidator.class);

    public void validateSessao(Sessao sessao) throws ValidationException {
        LOGGER.info("validateSessao " + sessao.toString());
        if(sessao == null){
            LOGGER.error("validateSessao error " + sessao.toString());
            throw new ValidationException("Sessão não pode ser null");
        }
        if(sessao.getIdPauta() == null){
            LOGGER.error("validateSessao error " + sessao.toString());
            throw new ValidationException("Id da Pauta não pode ser null");
        }
    }
}
