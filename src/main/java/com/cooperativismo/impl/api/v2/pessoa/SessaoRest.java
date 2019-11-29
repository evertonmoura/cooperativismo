package com.cooperativismo.impl.api.v2.pessoa;

import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.service.SessaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("cooperativismo/sessao/v2")
@RestController("SessaoRestV2")
public class SessaoRest {

    private SessaoService sessaoService;

    public SessaoRest(SessaoService sessaoService){
        this.sessaoService=sessaoService;
    }

    @ApiOperation(value = "Salva uma nova Sessão.", response = Void.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 422, message = "Erros de validação"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping(value = "/criar/")
    public ResponseEntity<SessaoDTO> criar(@RequestBody SessaoDTO sessaoDTO){
        return ResponseEntity.ok(sessaoService.saveSessao(sessaoDTO));
    }
}
