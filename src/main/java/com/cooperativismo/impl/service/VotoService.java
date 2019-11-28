package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.VotoConverter;
import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.Voto;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.repository.VotoRepository;
import com.cooperativismo.impl.service.client.PessoaIntegracaoClient;
import com.cooperativismo.impl.validator.VotoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.validator.ValidatorException;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {


   private VotoRepository votoRepository;
   private VotoConverter votoConverter;
   private VotoValidator votoValidator;
   private SessaoService sessaoService;
   private final PessoaIntegracaoClient pessoaIntegracaoClient;

    @Autowired
    public VotoService(VotoRepository votoRepository,VotoConverter votoConverter,VotoValidator votoValidator,SessaoService sessaoService,PessoaIntegracaoClient pessoaIntegracaoClient){
        this.votoRepository = votoRepository;
        this.votoConverter = votoConverter;
        this.votoValidator = votoValidator;
        this.sessaoService=sessaoService;
        this.pessoaIntegracaoClient=pessoaIntegracaoClient;
    }


    public  void votar(VotoDTO votoDTO){
        Voto voto = votoConverter.toEntity(votoDTO);
        votoValidator.validateVoto(voto);
        validarVotoAssociado(voto);
//        validarSessaoAberta(Long idPauta);
        votoRepository.save(voto);
    }


    public VotoDTO getVotoById(Long id){
        Optional<Voto> voto = votoRepository.findById(id);
        if(voto == null || !voto.isPresent()){
            //TODO voto não encontrado
        }
        return votoConverter.toDTO(voto.get());
    }

    private void validarVotoAssociado(Voto voto) {
            Optional<Voto> vt = votoRepository.findById(voto.getId());
//            if(vt.ifPresent(v -> v.getSessao().getId.equals); ){
//                //TODO no caso do usuario já ter votado na sessão deve retornar exceção
//
//            }
    }

    private Sessao validarSessaoAberta(Long idPauta) throws ValidatorException {
        Sessao sessao = sessaoService.buscarSessaoPorIdPauta(idPauta);
        if(sessao == null){
            throw new ValidatorException("Não existe sessão aberta para pauta.");
        }
        return sessao;
    }

    public VotoDTO votar(Long idPauta, SimNaoEnum opcao, String cpfAssociado) throws ValidatorException {
       Sessao sessao =  validarSessaoAberta(idPauta);
        Voto voto = new Voto();
        voto.setCpfAssociado(cpfAssociado);
        voto.setIdPauta(idPauta);
        voto.setIdSessao(sessao.getId());
        voto.setVoto(opcao);
            return votoConverter.toDTO(votoRepository.save(voto));
    }
}
