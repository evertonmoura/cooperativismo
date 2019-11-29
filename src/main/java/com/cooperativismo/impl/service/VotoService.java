package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.VotoConverter;
import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.Voto;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import com.cooperativismo.impl.entity.pessoa.Associado;
import com.cooperativismo.impl.repository.VotoRepository;
import com.cooperativismo.impl.service.pessoa.AssociadoService;
import com.cooperativismo.impl.validator.VotoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service("VotoService")
public class VotoService {

   private static  final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

   private VotoRepository votoRepository;
   private VotoConverter votoConverter;
   private VotoValidator votoValidator;
   private SessaoService sessaoService;
   private AssociadoService associadoService;

    @Autowired
    public VotoService(VotoRepository votoRepository,VotoConverter votoConverter,VotoValidator votoValidator,SessaoService sessaoService,AssociadoService associadoService){
        this.votoRepository = votoRepository;
        this.votoConverter = votoConverter;
        this.votoValidator = votoValidator;
        this.sessaoService=sessaoService;
        this.associadoService=associadoService;
    }

    public List<Voto> buscarTodos(){
        List<Voto> votos = new ArrayList<>();
        votoRepository.findAll().forEach(votos::add);
        return votos;
    }

    public VotoDTO votar(Long idPauta, SimNaoEnum opcao, String cpfAssociado) throws ValidationException {
        LOGGER.info("votar " + idPauta + " : " + opcao + " : "  + cpfAssociado);
        Sessao sessao =  validarSessao(idPauta);
        validarAssociado(cpfAssociado);
        validarVotoAssociado(cpfAssociado,idPauta);
        Voto voto = votoRepository.save(criarVoto(idPauta,opcao,cpfAssociado,sessao));
        LOGGER.info("votar OK " + voto.toString());
        return votoConverter.toDTO(voto);
    }

    public VotoDTO votar(VotoDTO votoDTO) throws ValidationException {
        LOGGER.info("votar " + votoDTO.toString());
        Sessao sessao = validarSessao(votoDTO.getIdPauta());
        votoDTO.setId(null);
        Voto voto = votoConverter.toEntity(votoDTO);
        votoValidator.validateVoto(voto);
        validarVotoAssociado(votoDTO.getCpfAssociado(),votoDTO.getIdPauta());
        voto = votoRepository.save(criarVoto(votoDTO,sessao));
        LOGGER.info("votar  OK ");
        return votoConverter.toDTO(voto);
    }


    private Sessao validarSessao(Long idPauta) throws ValidationException {
        LOGGER.info("validarSessaoAberta " + idPauta);
        Sessao sessao = sessaoService.buscarSessaoPorIdPauta(idPauta);
        if(sessao == null){
            LOGGER.error("validarSessaoAberta " + idPauta);
            throw new ValidationException("Não existe sessão aberta para pauta.");
        }
        if(sessao.getStatus().equals(StatusSessaoEnum.ENCERRADA)){
            LOGGER.error("validarSessaoAberta " + sessao.toString());
            throw new ValidationException("Sessão de votos para pauta já encerrada.");
        }
        LOGGER.info("validarSessaoAberta  OK ");
        return sessao;
    }

    private void validarAssociado(String cpfAssociado) {
        LOGGER.info("validarAssociado " + cpfAssociado);
        Associado associado = associadoService.buscarAssociadoPorCpf(cpfAssociado);
        if(associado == null){
            LOGGER.error("validarAssociado " + cpfAssociado);
            throw new ValidationException("Associado não cadastrado.");
        }
        LOGGER.info("validarAssociado  OK" + cpfAssociado);
    }

    private void validarVotoAssociado(String cpfAssociado, Long idPauta) {
        LOGGER.info("validarVotoAssociado " + cpfAssociado + " : " + idPauta);
        List<Voto> votos = votoRepository.findByCpfAssociado(cpfAssociado);
        if(votos.stream().filter(voto -> voto.getIdPauta().equals(idPauta)).count() > 0){
            LOGGER.error("validarVotoAssociado " + cpfAssociado + " : " + idPauta);
            throw new ValidationException("Associado já tem voto na pauta.");
        }
        LOGGER.info("validarVotoAssociado  OK" + cpfAssociado + " : " + idPauta);
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
        voto.setCpfAssociado(votoDTO.getCpfAssociado());
        voto.setIdPauta(votoDTO.getIdPauta());
        voto.setIdSessao(sessao.getId());
        voto.setVoto(votoDTO.getVoto());
        return  voto;
    }
}
