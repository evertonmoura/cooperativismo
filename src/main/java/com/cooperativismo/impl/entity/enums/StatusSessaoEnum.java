package com.cooperativismo.impl.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusSessaoEnum {
    ABERTA("Aberta"),
    ENCERRADA("Encerrada");

    private String descricao;
}
