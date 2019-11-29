package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Pauta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;


@Component
public class PautaValidator {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PautaValidator.class);

    public void validatePauta(Pauta pauta) throws ValidationException {
        LOGGER.info("validatePauta " + pauta.toString());
        if(pauta == null){
            LOGGER.error("validatePauta error" + pauta.toString());
            throw new ValidationException("Pauta não pode ser null");
        }
        if(pauta.getDescricao() == null){
            LOGGER.error("validatePauta error" + pauta.toString());
            throw new ValidationException("Descrição da pauta não pode ser null");
        }
        if(pauta.getDescricao() != null && pauta.getDescricao().length() > 2000){
            LOGGER.error("validatePauta error" + pauta.toString());
            throw new ValidationException("Descrição da pauta não pode ser maior que 2000 caracteres");
        }
        LOGGER.info("validatePauta OK" + pauta.toString());

    }
}
