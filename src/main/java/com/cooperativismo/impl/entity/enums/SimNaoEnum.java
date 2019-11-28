package com.cooperativismo.impl.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimNaoEnum {

    SIM("Sim"),
    NAO("Não");

    private String descricao;
}
