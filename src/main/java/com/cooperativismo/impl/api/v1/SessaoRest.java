package com.cooperativismo.impl.api.v1;

import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
@RestController
@RequestMapping("cooperativismo/sessao")
@Api(value = "API REST Sessão")
@CrossOrigin(origins = "*")
public class SessaoRest {


    public SessaoService sessaoService;

    @Autowired
    public SessaoRest(SessaoService sessaoService){
        this.sessaoService = sessaoService;
    }


    @ApiOperation(value = "Retorna uma Sessção.", response = PautaDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Sessão não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<SessaoDTO> buscarSessaoPorId(Long idSessao){
        return ResponseEntity.ok(sessaoService.buscarSessaoPorID(idSessao));
    }


    @ApiOperation(value = "Retorna uma lista de Sessão.", response = PautaDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Sessão não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @GetMapping(value = "/sessoes-status-aberta")
    public ResponseEntity<List<SessaoDTO>> buscarSessoesAbtertas(){
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Salva uma nova Sessão.", response = Void.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping(value = "/criar/pauta/{id}")
    public ResponseEntity<SessaoDTO> save(@PathVariable(name = "id") Long idPauta, @RequestParam(name = "minutos" , required = false) Integer minutos){
             return ResponseEntity.ok(sessaoService.saveSessao(idPauta,minutos));
    }

}
