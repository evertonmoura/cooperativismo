package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.SessaoConverter;
import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import com.cooperativismo.impl.repository.SessaoRepository;
import com.cooperativismo.impl.validator.SessaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private SessaoConverter sessaoConverter;
    private SessaoValidator sessaoValidator;

    @Autowired
    public SessaoService (SessaoRepository sessaoRepository,SessaoConverter sessaoConverter,SessaoValidator sessaoValidator){
            this.sessaoRepository=sessaoRepository;
            this.sessaoConverter=sessaoConverter;
            this.sessaoValidator=sessaoValidator;
    }

    public List<SessaoDTO> buscarTodasSessoesDTO(){
        List<Sessao> sessoes = new ArrayList<>();
        sessaoRepository.findAll().forEach(sessoes::add);
        return sessaoConverter.toListDTO(sessoes);
    }

    public List<Sessao> buscarTodasSessoes(){
        List<Sessao> sessoes = new ArrayList<>();
        sessaoRepository.findAll().forEach(sessoes::add);
        return sessoes;
    }
    public SessaoDTO buscarSessaoPorID(Long id){
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        if(sessao == null || !sessao.isPresent()){
            //Sessão não encontrada
        }
        return  sessaoConverter.toDTO(sessao.get());
    }

    public SessaoDTO saveSessao(SessaoDTO sessaoDTO){
        Sessao sessao = sessaoConverter.toEntity(sessaoDTO);
        sessaoValidator.validateSessao(sessao);
        return  sessaoConverter.toDTO(sessaoRepository.save(sessao));
    }

    public SessaoDTO saveSessao(long idPauta,Integer minutos){
        Sessao sessao = criarSessao(idPauta,minutos);
        sessaoValidator.validateSessao(sessao);
        return  sessaoConverter.toDTO(sessaoRepository.save(sessao));
    }

    public  Sessao buscarSessaoPorIdPauta(Long idPauta){
        return sessaoRepository.findByIdPauta(idPauta);
    }

    public Sessao atualizarSessao(Sessao sessao){
        return sessaoRepository.save(sessao);
    }

    private Sessao criarSessao(long idPauta, Integer minutos) {
        Sessao sessao = new Sessao();
        sessao.setDataHoraInicioSessao(LocalDateTime.now());
        sessao.setIdPauta(idPauta);
        sessao.setMinutosSessao(minutos == null ? 1 : minutos);
        sessao.setStatus(StatusSessaoEnum.ABERTA);
        return sessao;
    }
}
