package com.cooperativismo.impl.api.v1.pessoa;

import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.dto.pessoa.AssociadoDTO;
import com.cooperativismo.impl.entity.pessoa.Associado;
import com.cooperativismo.impl.service.pessoa.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("cooperativismo/associado")
@Api(value = "API REST Associado")
@CrossOrigin(origins = "*")
public class AssociadoRest {

    private AssociadoService associadoService;

    public AssociadoRest(AssociadoService associadoService){
    this.associadoService=associadoService;
    }
    @ApiOperation(value = "Salva um novo Associado.", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Erro inesperado") })
    @PostMapping(value = "/save/nome/{nome}/cpf/{cpf}")
    public ResponseEntity<AssociadoDTO> save(@PathVariable(name = "nome") String nome, @PathVariable(name = "cpf") String cpf) throws ValidationException {
        return ResponseEntity.ok(associadoService.save(nome,cpf));
    }
}
