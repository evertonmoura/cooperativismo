package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Voto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class VotoValidator {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SessaoValidator.class);

        public void validateVoto(Voto voto){
            LOGGER.info("validateVoto" + voto.toString());
        if(voto == null){
            LOGGER.error("validateVoto error" + voto.toString());
            throw  new ValidationException("Voto não pode ser null.");
        }
        if(voto.getIdPauta() == null){
            LOGGER.error("validateVoto error" + voto.toString());
            throw  new ValidationException("ID da Pauta não pode ser null.");
        }
        if(voto.getVoto() == null){
            LOGGER.error("validateVoto error" + voto.toString());
            throw  new ValidationException("Opção do voto não pode ser null.");
        }
    }
}
