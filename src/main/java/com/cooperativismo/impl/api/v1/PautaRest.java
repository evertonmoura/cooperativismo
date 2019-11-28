package com.cooperativismo.impl.api.v1;

import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.entity.Pauta;
import com.cooperativismo.impl.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("cooperativismo/pauta")
@Api(value = "API REST PAUTA")
@CrossOrigin(origins = "*")
public class PautaRest {

    private PautaService pautaService;

    @Autowired
    public PautaRest(PautaService pautaService){
        this.pautaService=pautaService;
    }

    @ApiOperation(value = "Retorna a Pauta.", response = PautaDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Pauta não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PautaDTO> getPauta(Long idPauta){
              return ResponseEntity.ok(pautaService.getPautaById(idPauta));
    }

    @ApiOperation(value = "Retorna uma lista com as pautas em  Situação = Aberta.", response = PautaDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Nenhuma pauta em aberto encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @GetMapping(value = "/abertas")
    public ResponseEntity<List<PautaDTO>> getAllPautas(){
        return ResponseEntity.ok(pautaService.getAllPautas());
    }


    @ApiOperation(value = "Insere uma nova pauta.", response = Void.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping("/save/{descricaoPauta}")
    public ResponseEntity<Void> save(@PathVariable(name = "descricaoPauta") String descricaoPauta) throws ValidationException {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setDescricao(descricaoPauta);
        pautaService.savaPauta(pautaDTO);
        return ResponseEntity.ok().build();
    }


}
