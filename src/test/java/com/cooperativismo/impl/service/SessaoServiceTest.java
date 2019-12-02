package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.SessaoConverter;
import com.cooperativismo.impl.dto.SessaoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.exception.ValidationException;
import com.cooperativismo.impl.rabbit.Sender;
import com.cooperativismo.impl.repository.SessaoRepository;
import com.cooperativismo.impl.validator.SessaoValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.cooperativismo.impl.entity.enums.StatusSessaoEnum.ENCERRADA;
import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.*;

public class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository = mock(SessaoRepository.class);
    @Mock
    private SessaoValidator sessaoValidator = mock(SessaoValidator.class);
    @Mock
    private SessaoConverter sessaoConverter = mock(SessaoConverter.class);
    @Mock
    private Sender sender = mock(Sender.class);

    @InjectMocks
    private SessaoService sessaoService = new SessaoService(sessaoRepository,sessaoConverter,sessaoValidator,sender);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deveBuscarSessaoPorIdPauta(){
        when(sessaoRepository.findByIdPauta(1L)).thenReturn(buildSessao());
        sessaoService.buscarSessaoPorIdPauta(1L);
    }

    @Test
    public void deveBuscarSessaoPorId(){
        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(buildSessao()));
        sessaoService.buscarSessaoPorIdPauta(1L);
    }

    @Test
    public void deveValidarSessao(){
        Sessao sessao = null;
        thrown.expect(ValidationException.class);
        when(sessaoConverter.toDTO(sessao)).thenReturn(null);
        doThrow(new ValidationException()).when(sessaoValidator).validateSessao(sessao);
        sessaoService.saveSessao(new SessaoDTO());
    }

    @Test
    public void deveValidarSessaoSemPauta(){
        thrown.expect(ValidationException.class);
        Sessao sessao = buildSessao();
        sessao.setIdPauta(null);
        SessaoDTO sessaoDTO = buildSessaoDTO();
        when(sessaoConverter.toEntity(sessaoDTO)).thenReturn(sessao);
        doThrow(new ValidationException()).when(sessaoValidator).validateSessao(sessao);
        sessaoService.saveSessao(sessaoDTO);
    }

    @Test
    public void deveSalvarSessao(){
        Sessao sessao = buildSessao();
        SessaoDTO sessaoDTO = buildSessaoDTO();
        when(sessaoConverter.toEntity(sessaoDTO)).thenReturn(sessao);
        when(sessaoRepository.findByIdPauta(1L)).thenReturn(null);
        when(sessaoConverter.toDTO(sessao)).thenReturn(sessaoDTO);
        when(sessaoRepository.save(sessao)).thenReturn(sessao);
        sessaoService.saveSessao(sessaoDTO);


    }

    @Test
    public void naoDeveSalvarSessaovaJaExistenteParaPauta(){
        thrown.expect(ValidationException.class);
        Sessao sessao = buildSessao();
        SessaoDTO sessaoDTO = buildSessaoDTO();
        when(sessaoConverter.toEntity(sessaoDTO)).thenReturn(sessao);
        when(sessaoRepository.findByIdPauta(1L)).thenReturn(sessao);
        sessaoService.saveSessao(sessaoDTO);
    }

    private Sessao buildSessao() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setStatus(ENCERRADA);
        sessao.setMinutosSessao(1);
        sessao.setDataHoraInicioSessao(now());
        sessao.setIdPauta(1L);
        sessao.setQuantidadeVotos(1L);
        sessao.setQuantidadeVotosSim(1L);
        sessao.setQuantidadeVotosNao(0);
        sessao.setDataHoraFimSessao(now().plusMinutes(sessao.getMinutosSessao()));
        return sessao;
    }

    private SessaoDTO buildSessaoDTO() {
        SessaoDTO sessao = new SessaoDTO();
        sessao.setId(1L);
        sessao.setStatus(ENCERRADA);
        sessao.setMinutosSessao(1);
        sessao.setDataHoraInicioSessao(now());
        sessao.setIdPauta(1L);
        sessao.setQuantidadeVotos(1L);
        sessao.setQuantidadeVotosSim(1L);
        sessao.setQuantidadeVotosNao(0);
        sessao.setDataHoraFimSessao(now().plusMinutes(sessao.getMinutosSessao()));
        return sessao;
    }
}
