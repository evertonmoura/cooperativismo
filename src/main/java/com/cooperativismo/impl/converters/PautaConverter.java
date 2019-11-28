package com.cooperativismo.impl.converters;

import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.entity.Pauta;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaConverter {

    private ModelMapper mapper;

    public PautaConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Pauta toEntity(PautaDTO pautaDTO){
        Assert.notNull(pautaDTO, "PautaDTO não pode ser nulo para a conversão em Pauta");
        return mapper.map(pautaDTO, Pauta.class);
    }

    public PautaDTO toDTO(Pauta pauta){
        Assert.notNull(pauta, "Pauta não pode ser nulo para a conversão em PautaDTO");
        return mapper.map(pauta, PautaDTO.class);
    }

    public List<PautaDTO> toDTOList(List<Pauta> pautas){
        Assert.notNull(pautas, "Pauta não pode ser nulo para a conversão em PautaDTO");
        List<PautaDTO> collect = pautas.stream().map(pauta -> toDTO(pauta)).collect(Collectors.toList());
        return collect;
    }
}
