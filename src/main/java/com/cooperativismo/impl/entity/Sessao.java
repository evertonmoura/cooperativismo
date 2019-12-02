package com.cooperativismo.impl.entity;

import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Sessao implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull(message = "A pauta deve ser informada")
    private Long idPauta;
    @NotNull(message = "O tempo de duração da sessão deve ser informado.")
    private Integer minutosSessao;
    private LocalDateTime dataHoraInicioSessao;
    private LocalDateTime dataHoraFimSessao;
    private StatusSessaoEnum status;
    private long quantidadeVotos;
    private long quantidadeVotosSim;
    private long quantidadeVotosNao;

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

    public long getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public void setQuantidadeVotos(long quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }

    public long getQuantidadeVotosSim() {
        return quantidadeVotosSim;
    }

    public void setQuantidadeVotosSim(long quantidadeVotosSim) {
        this.quantidadeVotosSim = quantidadeVotosSim;
    }

    public long getQuantidadeVotosNao() {
        return quantidadeVotosNao;
    }

    public void setQuantidadeVotosNao(long quantidadeVotosNao) {
        this.quantidadeVotosNao = quantidadeVotosNao;
    }

    public LocalDateTime getDataHoraFimSessao() {
        return dataHoraFimSessao;
    }

    public void setDataHoraFimSessao(LocalDateTime dataHoraFimSessao) {
        this.dataHoraFimSessao = dataHoraFimSessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Sessao sessao = (Sessao) o;

        return new EqualsBuilder()
                .append(quantidadeVotos, sessao.quantidadeVotos)
                .append(quantidadeVotosSim, sessao.quantidadeVotosSim)
                .append(quantidadeVotosNao, sessao.quantidadeVotosNao)
                .append(id, sessao.id)
                .append(idPauta, sessao.idPauta)
                .append(minutosSessao, sessao.minutosSessao)
                .append(dataHoraInicioSessao, sessao.dataHoraInicioSessao)
                .append(dataHoraFimSessao, sessao.dataHoraFimSessao)
                .append(status, sessao.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(idPauta)
                .append(minutosSessao)
                .append(dataHoraInicioSessao)
                .append(dataHoraFimSessao)
                .append(status)
                .append(quantidadeVotos)
                .append(quantidadeVotosSim)
                .append(quantidadeVotosNao)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("idPauta", idPauta)
                .append("minutosSessao", minutosSessao)
                .append("dataHoraInicioSessao", dataHoraInicioSessao)
                .append("dataHoraFimSessao", dataHoraFimSessao)
                .append("status", status)
                .append("quantidadeVotos", quantidadeVotos)
                .append("quantidadeVotosSim", quantidadeVotosSim)
                .append("quantidadeVotosNao", quantidadeVotosNao)
                .toString();
    }
}
