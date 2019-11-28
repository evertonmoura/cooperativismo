package com.cooperativismo.impl.converters;

import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.Voto;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VotoConverter {

    private ModelMapper mapper;

    public VotoConverter (ModelMapper mapper){
        this.mapper = mapper;
    }

    public VotoDTO toDTO(Voto entity){
        Assert.notNull(entity, "Voto não pode ser nulo para a conversão em VotoDTO");
        return mapper.map(entity, VotoDTO.class);
    }

    public Voto toEntity(VotoDTO votoDTO){
        Assert.notNull(votoDTO, "VotoDTO não pode ser nulo para a conversão em Voto");
        return mapper.map(votoDTO, Voto.class);
    }


    public List<VotoDTO> toListDTO(List<Voto> votos){
        Assert.notNull(votos, "Voto não pode ser nulo para a conversão em VotoDTO");
        return null;
    }
}
