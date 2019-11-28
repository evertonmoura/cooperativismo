package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Pauta;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

import javax.xml.bind.ValidationException;

@Component
public class PautaValidator {

    public void validatePauta(Pauta pauta) throws ValidationException {
        if(pauta == null){
            throw new ValidationException("Pauta não pode ser null");
        }
        if(pauta.getDescricao() == null){
            throw new ValidationException("Descrição da pauta não pode ser null");
        }
        if(pauta.getDescricao() != null && pauta.getDescricao().length() > 2000){
            throw new ValidationException("Descrição da pauta não pode ser maior que 2000 caracteres");
        }
    }
}
