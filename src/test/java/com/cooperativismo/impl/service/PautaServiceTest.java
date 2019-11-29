package com.cooperativismo.impl.service;

import com.cooperativismo.impl.converters.PautaConverter;
import com.cooperativismo.impl.dto.PautaDTO;
import com.cooperativismo.impl.entity.Pauta;
import com.cooperativismo.impl.repository.PautaRepository;
import com.cooperativismo.impl.validator.PautaValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.ValidationException;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository = Mockito.mock(PautaRepository.class);
    @Mock
    private PautaConverter pautaConverter =  Mockito.mock(PautaConverter.class);
    @Mock
    private PautaValidator pautaValidator=  Mockito.mock(PautaValidator.class);

    @InjectMocks
    private PautaService pautaService = new PautaService(pautaRepository,pautaConverter,pautaValidator);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deveSalvarPauta(){
        Pauta novaPauta = buildPauta();
        when(pautaRepository.save(novaPauta)).thenReturn(buildPautaId());
        when(pautaConverter.toEntity(buildPautaDTO())).thenReturn(novaPauta);
        when(pautaConverter.toDTO(buildPautaId())).thenReturn(buildPautaIdDTO());
        PautaDTO salva =  pautaService.salvaPauta(buildPautaDTO());
        assertEquals(buildPautaId().getId(),salva.getId());
    }

    @Test
    public void deveRetornarErroValidacaoPauta(){
        Pauta novaPauta = null;
        thrown.expect(ValidationException.class);
        when(pautaRepository.save(novaPauta)).thenReturn(buildPautaId());
        Mockito.doThrow(new ValidationException()).when(pautaValidator).validatePauta(novaPauta);
        pautaService.salvaPauta(buildPautaDTO());
    }

    @Test
    public void deveBuscarPauta(){
        when(pautaRepository.findById(1L)).thenReturn(of(buildPautaId()));
        when(pautaConverter.toDTO(buildPautaId())).thenReturn(buildPautaIdDTO());
        PautaDTO pautaDTO =  pautaService.getPautaById(1L);
        assertNotNull(pautaDTO.getId());
    }

    private PautaDTO buildPautaDTO() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setDescricao("Teste");
        return pautaDTO;
    }

    private PautaDTO buildPautaIdDTO() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(1L);
        pautaDTO.setDescricao("Teste");
        return pautaDTO;
    }

    private Pauta buildPauta() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Teste");
        return pauta;
    }

    private Pauta buildPautaId() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setDescricao("Teste");
        return pauta;
    }

}
