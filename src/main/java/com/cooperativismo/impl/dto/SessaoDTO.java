package com.cooperativismo.impl.dto;

import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SessaoDTO implements Serializable {

    private Long id;
    private Long idPauta;
    private Integer minutosSessao;
    private LocalDateTime dataHoraInicioSessao;
    private LocalDateTime dataHoraFimSessao;
    private StatusSessaoEnum status;
    private long quantidadeVotos;
    private long quantidadeVotosSim;
    private long quantidadeVotosNao;

}
