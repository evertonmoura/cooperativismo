package com.cooperativismo.impl.dto;

import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import lombok.Data;

@Data
public class VotoDTO {


    private Long id;
    private Long numeroVoto;
    private SimNaoEnum voto;
    private String cpfAssociado;
    private Long idPauta;

}
