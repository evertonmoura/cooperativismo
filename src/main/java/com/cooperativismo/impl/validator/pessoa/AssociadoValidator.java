package com.cooperativismo.impl.validator.pessoa;

import com.cooperativismo.impl.entity.pessoa.Associado;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class AssociadoValidator {

    public void validateAssociado(Associado associado) throws ValidationException {
        if(associado == null){
            throw new ValidationException("Associado não pode ser null");
        }
        if(associado.getNome() == null){
            throw new ValidationException("Nome associado  não pode ser null");
        }
        if(associado.getCpf() == null){
            throw new ValidationException("CPF associado não pode ser null");
        }
    }
}
