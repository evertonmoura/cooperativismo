package com.cooperativismo.impl.dto;

import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SessaoDTO {

    private Long idPauta;
    private Integer minutosSessao;
    private LocalDateTime dataHoraInicioSessao;
    private StatusSessaoEnum status;
    private Integer quantidadeVotosRecebidos;

}
