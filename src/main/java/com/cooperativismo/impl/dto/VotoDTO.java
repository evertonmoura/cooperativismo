package com.cooperativismo.impl.dto;

import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class VotoDTO implements Serializable {


    private Long id;
    private SimNaoEnum voto;
    private String cpfAssociado;
    private Long idPauta;

}
