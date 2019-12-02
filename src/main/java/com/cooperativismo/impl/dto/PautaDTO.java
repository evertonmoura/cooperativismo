package com.cooperativismo.impl.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PautaDTO implements Serializable {

    private Long id;
    private String descricao;

}
