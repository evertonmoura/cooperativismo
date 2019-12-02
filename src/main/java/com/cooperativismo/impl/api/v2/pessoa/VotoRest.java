package com.cooperativismo.impl.api.v2.pessoa;

import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.service.v2.VotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("cooperativismo/voto/v2")
@RestController("VotoRestV2")
public class VotoRest {

    private VotoService votoServiceV2;

    @Autowired
    public VotoRest(VotoService votoServiceV2) {
        this.votoServiceV2=votoServiceV2;
    }


    @ApiOperation(value = "Salva um  voto em uma sessão aberta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Erros de validação"),
            @ApiResponse(code = 404, message = "Pauta não tem sessão aberta"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping("/votar")
    public ResponseEntity<VotoDTO> votar(@RequestBody VotoDTO votoDTO){
        return ResponseEntity.ok(votoServiceV2.votar(votoDTO));
    }
}
