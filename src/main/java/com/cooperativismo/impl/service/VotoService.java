package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.VotoConverter;
import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.Voto;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.entity.pessoa.Associado;
import com.cooperativismo.impl.repository.VotoRepository;
import com.cooperativismo.impl.service.client.PessoaIntegracaoClient;
import com.cooperativismo.impl.service.pessoa.AssociadoService;
import com.cooperativismo.impl.validator.VotoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.validator.ValidatorException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service("VotoService")
public class VotoService {


   private VotoRepository votoRepository;
   private VotoConverter votoConverter;
   private VotoValidator votoValidator;
   private SessaoService sessaoService;
   private AssociadoService associadoService;
   private final PessoaIntegracaoClient pessoaIntegracaoClient;

    @Autowired
    public VotoService(VotoRepository votoRepository,VotoConverter votoConverter,VotoValidator votoValidator,SessaoService sessaoService,AssociadoService associadoService,PessoaIntegracaoClient pessoaIntegracaoClient){
        this.votoRepository = votoRepository;
        this.votoConverter = votoConverter;
        this.votoValidator = votoValidator;
        this.sessaoService=sessaoService;
        this.associadoService=associadoService;
        this.pessoaIntegracaoClient=pessoaIntegracaoClient;
    }

    public List<Voto> buscarTodos(){
        List<Voto> votos = new ArrayList<>();
        votoRepository.findAll().forEach(votos::add);
        return votos;
    }

    public VotoDTO votar(Long idPauta, SimNaoEnum opcao, String cpfAssociado) throws ValidatorException {
        Sessao sessao =  validarSessaoAberta(idPauta);
        validarAssociado(cpfAssociado);
        validarVotoAssociado(cpfAssociado,idPauta);
        return votoConverter.toDTO(votoRepository.save(criarVoto(idPauta,opcao,cpfAssociado,sessao)));
    }

    public VotoDTO votar(VotoDTO votoDTO) throws ValidatorException {
        Sessao sessao = validarSessaoAberta(votoDTO.getIdPauta());
        votoDTO.setId(null);
        Voto voto = votoConverter.toEntity(votoDTO);
        votoValidator.validateVoto(voto);
        validarVotoAssociado(votoDTO.getCpfAssociado(),votoDTO.getIdPauta());
        voto = votoRepository.save(criarVoto(votoDTO,sessao));
        return votoConverter.toDTO(voto);
    }


    private Sessao validarSessaoAberta(Long idPauta) throws ValidatorException {
        Sessao sessao = sessaoService.buscarSessaoPorIdPauta(idPauta);
        if(sessao == null){
            throw new ValidationException("Não existe sessão aberta para pauta.");
        }
        return sessao;
    }

    private void validarAssociado(String cpfAssociado) {
        Associado associado = associadoService.buscarAssociadoPorCpf(cpfAssociado);
        if(associado == null){
            throw new ValidationException("Associado não cadastrado.");
        }
    }

    private void validarVotoAssociado(String cpfAssociado, Long idPauta) {
        List<Voto> votos = votoRepository.findByCpfAssociado(cpfAssociado);
        if(votos.stream().filter(voto -> voto.getIdPauta().equals(idPauta)).count() > 0){
            throw new ValidationException("Associado já tem voto na pauta.");

        }
    }


    private Voto criarVoto(Long idPauta, SimNaoEnum opcao, String cpfAssociado, Sessao sessao) {
        Voto voto = new Voto();
        voto.setCpfAssociado(cpfAssociado);
        voto.setIdPauta(idPauta);
        voto.setIdSessao(sessao.getId());
        voto.setVoto(opcao);
        return voto;
    }

    private Voto criarVoto(VotoDTO votoDTO, Sessao sessao) {
        Voto voto = new Voto();
        voto.setCpfAssociado(voto.getCpfAssociado());
        voto.setIdPauta(voto.getIdPauta());
        voto.setIdSessao(sessao.getId());
        voto.setVoto(votoDTO.getVoto());
        return  voto;
    }
}
