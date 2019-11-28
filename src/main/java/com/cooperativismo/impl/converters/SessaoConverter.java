package com.cooperativismo.impl.converters;

import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.entity.Sessao;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class SessaoConverter {

    private ModelMapper mapper;

    public SessaoConverter (ModelMapper mapper){
        this.mapper = mapper;
    }

    public Sessao toEntity(SessaoDTO sessaoDTO){
        Assert.notNull(sessaoDTO, "SessaoDTO não pode ser nulo para a conversão em Sessao");
        return mapper.map(sessaoDTO, Sessao.class);
    }

    public SessaoDTO toDTO(Sessao sessao){
        Assert.notNull(sessao, "Sessao não pode ser nulo para a conversão em SessaoDTO");
        return mapper.map(sessao, SessaoDTO.class);
    }

    public List<SessaoDTO> toListDTO(List<Sessao> sessoes){
        Assert.notNull(sessoes, "Sessao não pode ser nulo para a conversão em SessaoDTO");
        List<SessaoDTO> collect = sessoes.stream().map(sessao -> toDTO(sessao)).collect(toList());
        return collect;
    }
}
