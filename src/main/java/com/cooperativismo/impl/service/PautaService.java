package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.PautaConverter;
import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.entity.Pauta;
import com.cooperativismo.impl.repository.PautaRepository;
import com.cooperativismo.impl.service.pessoa.AssociadoService;
import com.cooperativismo.impl.validator.PautaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {


    private static  final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);


    private PautaRepository pautaRepository;
    private PautaConverter pautaConverter;
    private PautaValidator pautaValidator;

    @Autowired
    public  PautaService(PautaRepository pautaRepository,PautaConverter pautaConverter,PautaValidator pautaValidator){
        this.pautaRepository = pautaRepository;
        this.pautaConverter = pautaConverter;
        this.pautaValidator = pautaValidator;
    }


    public List<PautaDTO> getAllPautas(){
        List<Pauta> pautas = (List<Pauta>) pautaRepository.findAll();
        return pautaConverter.toDTOList(pautas);
    }


    public PautaDTO getPautaById(Long id){
        Optional<Pauta> pauta = pautaRepository.findById(id);
        return pautaConverter.toDTO(pauta.get());
    }

    public  PautaDTO savaPauta(PautaDTO pautaDTO) throws ValidationException {
        LOGGER.info("Salvando pauta:  "  + pautaDTO.getDescricao());
        Pauta pauta = pautaConverter.toEntity(pautaDTO);
        pautaValidator.validatePauta(pauta);
        PautaDTO pautaSalva = pautaConverter.toDTO(pautaRepository.save(pauta));
        LOGGER.info("Salvando pauta:  OK  "  + pautaDTO.getDescricao());
        return  pautaSalva;
    }
}
