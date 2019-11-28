package com.cooperativismo.impl.api.v1;

import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.validator.ValidatorException;

@RestController
@RequestMapping("cooperativismo/voto")
@Api(value = "API REST VOTO")
@CrossOrigin(origins = "*")
public class VotoRest {

    private VotoService votoService;


    @Autowired
    public VotoRest (VotoService votoService){
        this.votoService = votoService;
    }


    @ApiOperation(value = "Salva um  voto em uma sessão aberta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Pauta não tem sessão aberta"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping("/votar/pauta/{idPauta}/opcao/{opcao}/associado/{cpf}")
    public ResponseEntity<Void> votar(@PathVariable(name = "idPauta") Long idPauta,
                                      @PathVariable(name = "opcao")SimNaoEnum opcao,
                                      @PathVariable(name = "cpf") String cpfAssociado) throws ValidatorException {
        votoService.votar(idPauta,opcao,cpfAssociado);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Retorna um voto por id.", response = PautaDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Pauta não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @GetMapping(value = "/buscar/{cpf}/associado")
    public ResponseEntity<VotoDTO> buscarVotoPorCpfAssociado(Long idVoto){
        return ResponseEntity.ok().build();
    }
}
