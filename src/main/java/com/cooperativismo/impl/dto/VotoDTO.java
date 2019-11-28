package com.cooperativismo.impl.dto;

import com.cooperativismo.impl.entity.Pauta;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.entity.pessoa.Associado;
import lombok.Data;

@Data
public class VotoDTO {

    private Long id;
    private SimNaoEnum voto;
    private String cpfAssociado;
    private Long idPauta;

}
