package com.cooperativismo.impl.converters.pessoa;

import com.cooperativismo.impl.dto.pessoa.AssociadoDTO;
import com.cooperativismo.impl.entity.pessoa.Associado;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

@Component
public class AssociadoConverter {
    private ModelMapper mapper;

    public AssociadoConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Associado toEntity(AssociadoDTO associadoDTO){
        Assert.notNull(associadoDTO, "AssociadoDTO não pode ser nulo para a conversão em Associado");
        return mapper.map(associadoDTO, Associado.class);
    }

    public AssociadoDTO toDTO(Associado associado){
        Assert.notNull(associado, "Associado não pode ser nulo para a conversão em AssociadoDTO");
        return mapper.map(associado, AssociadoDTO.class);
    }

}
