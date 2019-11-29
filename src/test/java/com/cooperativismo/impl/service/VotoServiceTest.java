package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.VotoConverter;
import com.cooperativismo.impl.dto.VotoDTO;
import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.Voto;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.entity.enums.StatusSessaoEnum;
import com.cooperativismo.impl.repository.VotoRepository;
import com.cooperativismo.impl.service.pessoa.AssociadoService;
import com.cooperativismo.impl.validator.VotoValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.ValidationException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;

public class VotoServiceTest {
    @Mock
    private VotoRepository votoRepository = Mockito.mock(VotoRepository.class);
    @Mock
    private VotoConverter votoConverter = Mockito.mock(VotoConverter.class);
    @Mock
    private VotoValidator votoValidator = Mockito.mock(VotoValidator.class);
    @Mock
    private SessaoService sessaoService = Mockito.mock(SessaoService.class);
    @Mock
    private AssociadoService associadoService = Mockito.mock(AssociadoService.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private VotoService votoService = new VotoService(votoRepository,votoConverter,votoValidator,sessaoService,associadoService);

    @Test
    public void deveVotar(){
        VotoDTO votoDTO = buildVotoDTO();
        Voto voto = buildVoto();
        Mockito.when(sessaoService.buscarSessaoPorIdPauta(1L)).thenReturn(buildSessao());
        Mockito.when(votoConverter.toEntity(votoDTO)).thenReturn(voto);
        Mockito.when(votoRepository.findByCpfAssociado(voto.getCpfAssociado())).thenReturn(Collections.emptyList());
        Mockito.when(votoRepository.save(voto)).thenReturn(voto);
        Mockito.when(votoConverter.toDTO(voto)).thenReturn(votoDTO);
        votoService.votar(votoDTO);
        assertEquals(voto.getId(),votoDTO.getId());

    }

    @Test
    public void naoDeveVotarValidacaoSessaoNull(){
        thrown.expect(ValidationException.class);
        VotoDTO votoDTO = buildVotoDTO();
        Mockito.when(sessaoService.buscarSessaoPorIdPauta(1L)).thenReturn(null);
        votoService.votar(votoDTO);

    }
    @Test
    public void naoDeveVotarValidacaoSessaoEncerrada(){
        Sessao sessao = buildSessao();
        sessao.setStatus(StatusSessaoEnum.ENCERRADA);
        thrown.expect(ValidationException.class);
        VotoDTO votoDTO = buildVotoDTO();
        Mockito.when(sessaoService.buscarSessaoPorIdPauta(1L)).thenReturn(sessao);
        votoService.votar(votoDTO);

    }

    @Test
    public void naoDeveVotarAssociadoJaVotou(){
        Sessao sessao = buildSessao();
        thrown.expect(ValidationException.class);
        VotoDTO votoDTO = buildVotoDTO();
        Mockito.when(sessaoService.buscarSessaoPorIdPauta(1L)).thenReturn(sessao);
        List<Voto> votos = new ArrayList<>();
        votos.add(buildVoto());
        Mockito.when(votoRepository.findByCpfAssociado(votoDTO.getCpfAssociado())).thenReturn(votos);
        Mockito.when(sessaoService.buscarSessaoPorIdPauta(1L)).thenReturn(sessao);

        votoService.votar(votoDTO);

    }
    private Sessao buildSessao() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setStatus(StatusSessaoEnum.ABERTA);
        sessao.setMinutosSessao(1);
        sessao.setDataHoraInicioSessao(now());
        sessao.setIdPauta(1L);
        sessao.setQuantidadeVotos(1L);
        sessao.setQuantidadeVotosSim(1L);
        sessao.setQuantidadeVotosNao(0);
        sessao.setDataHoraFimSessao(now().plusMinutes(sessao.getMinutosSessao()));
        return sessao;
    }
    private VotoDTO buildVotoDTO() {
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setCpfAssociado("12345678974");
        votoDTO.setIdPauta(1L);
        votoDTO.setVoto(SimNaoEnum.SIM);
        return  votoDTO;
    }

    private Voto buildVoto() {
        Voto voto = new Voto();
        voto.setCpfAssociado("12345678974");
        voto.setIdPauta(1L);
        voto.setVoto(SimNaoEnum.SIM);
        return voto;
    }

}
