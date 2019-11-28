package com.cooperativismo.impl.validator;

import com.cooperativismo.impl.entity.Sessao;
import org.springframework.stereotype.Component;

@Component
public class SessaoValidator {

    public SessaoValidator(){

    }

    public void validateSessao(Sessao sessao) {
        if(sessao == null){
            //Sessção não pdoe ser null
        }
//        if(sessao.getPauta() == null){
//            //Sessão deve ter uma pauta
//        }
    }
}
