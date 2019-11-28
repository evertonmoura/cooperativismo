package com.cooperativismo.impl.entity;

import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Sessao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull(message = "A pauta deve ser informada")
    private Long idPauta;
    @NotNull(message = "O tempo de duração da sessão deve ser informado.")
    private Integer minutosSessao;
    private LocalDateTime dataHoraInicioSessao;
    private StatusSessaoEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getMinutosSessao() {
        return minutosSessao;
    }

    public void setMinutosSessao(Integer minutosSessao) {
        this.minutosSessao = minutosSessao;
    }

    public LocalDateTime getDataHoraInicioSessao() {
        return dataHoraInicioSessao;
    }

    public void setDataHoraInicioSessao(LocalDateTime dataHoraInicioSessao) {
        this.dataHoraInicioSessao = dataHoraInicioSessao;
    }

    public StatusSessaoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusSessaoEnum status) {
        this.status = status;
    }
}
