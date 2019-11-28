package com.cooperativismo.impl.entity.pessoa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Associado {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotNull(message = "O nome do associado deve ser informado.")
    private String nome;
    @NotNull(message = "O cpf do associado deve ser informado")
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Associado associado = (Associado) o;

        return new EqualsBuilder()
                .append(id, associado.id)
                .append(nome, associado.nome)
                .append(cpf, associado.cpf)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(nome)
                .append(cpf)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .append("cpf", cpf)
                .toString();
    }
}
