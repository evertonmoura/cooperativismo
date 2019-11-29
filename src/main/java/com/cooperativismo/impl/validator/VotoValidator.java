package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Voto;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class VotoValidator {

    public void validateVoto(Voto voto){
        if(voto == null){
            throw  new ValidationException("Voto não pode ser null.");
        }
        if(voto.getIdPauta() == null){
            throw  new ValidationException("ID da Pauta não pode ser null.");
        }
        if(voto.getVoto() == null){
            throw  new ValidationException("Opção do voto não pode ser null.");
        }
    }
}
