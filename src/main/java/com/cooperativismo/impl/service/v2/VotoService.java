package com.cooperativismo.impl.service.v2;

import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.service.client.PessoaIntegracaoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.validator.ValidatorException;

import javax.validation.ValidationException;

import static java.lang.String.valueOf;

@Service("VotoServiceV2")
public class VotoService {

   private static  final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

   private  PessoaIntegracaoClient pessoaIntegracaoClient;
   private com.cooperativismo.impl.service.VotoService votoService;

   @Autowired
   public VotoService(PessoaIntegracaoClient pessoaIntegracaoClient, com.cooperativismo.impl.service.VotoService votoService){
       this.pessoaIntegracaoClient=pessoaIntegracaoClient;
       this.votoService=votoService;
   }

   public VotoDTO votar(VotoDTO voto) throws ValidatorException {
       LOGGER.info("Salvando voto V2 : " + voto.toString());
       validarUsuarioAptoVoto(voto.getCpfAssociado());
       return votoService.votar(voto);

   }

    private void validarUsuarioAptoVoto(String cpf) {
        LOGGER.info("validarUsuarioAptoVoto  : " + cpf);
        String retorno = valueOf(pessoaIntegracaoClient.validar(cpf));
       if(retorno != null && retorno.contains("UNABLE_TO_VOTE")){
           LOGGER.error("Asssociado não pode votar: " + cpf);
            throw new ValidationException("Asssociado não pode votar.");
        }
        LOGGER.info("validarUsuarioAptoVoto  :  OK " + cpf);
    }

}
