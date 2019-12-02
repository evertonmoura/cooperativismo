package com.cooperativismo.impl.entity;

import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "A escolha entre Sim/Não do voto é obrigatoria.")
    private SimNaoEnum voto;
    private String cpfAssociado;
    private Long idPauta;
    private Long idSessao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimNaoEnum getVoto() {
        return voto;
    }

    public void setVoto(SimNaoEnum voto) {
        this.voto = voto;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Voto voto1 = (Voto) o;

        return new EqualsBuilder()
                .append(id, voto1.id)
                .append(voto, voto1.voto)
                .append(cpfAssociado, voto1.cpfAssociado)
                .append(idPauta, voto1.idPauta)
                .append(idSessao, voto1.idSessao)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(voto)
                .append(cpfAssociado)
                .append(idPauta)
                .append(idSessao)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("voto", voto)
                .append("cpfAssociado", cpfAssociado)
                .append("idPauta", idPauta)
                .append("idSessao", idSessao)
                .toString();
    }
}
