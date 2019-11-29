package com.cooperativismo.impl.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "associados", url = "${integracao.url}")
public interface PessoaIntegracaoClient {

    @RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
    ResponseEntity<String> validar(
    @RequestParam("cpf") String cpf);
}
