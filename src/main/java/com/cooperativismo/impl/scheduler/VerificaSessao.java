package com.cooperativismo.impl.scheduler;

import com.cooperativismo.impl.entity.Sessao;
import com.cooperativismo.impl.entity.Voto;
import com.cooperativismo.impl.entity.enums.SimNaoEnum;
import com.cooperativismo.impl.service.SessaoService;
import com.cooperativismo.impl.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.cooperativismo.impl.entity.enums.SimNaoEnum.NAO;
import static com.cooperativismo.impl.entity.enums.SimNaoEnum.SIM;
import static com.cooperativismo.impl.entity.enums.StatusSessaoEnum.ABERTA;
import static com.cooperativismo.impl.entity.enums.StatusSessaoEnum.ENCERRADA;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.stream.Collectors.toList;

@Component
@EnableScheduling
public class VerificaSessao {

    private static  final Logger LOGGER = LoggerFactory.getLogger(VerificaSessao.class);
    private SessaoService sessaoService;
    private VotoService votoService;

    @Autowired
    public VerificaSessao(SessaoService sessaoService,VotoService votoService) {
        this.sessaoService = sessaoService;
        this.votoService=votoService;
    }

    @Scheduled(fixedDelay = 10000)
    public void verificaValidadeSessoesEmAberto() {
        LOGGER.info("Iiniciano rotina de encerramento das sessões de votos.");
        List<Sessao> sessaoList = sessaoService.buscarTodasSessoes();
        atualizarSessao(sessaoList.stream().filter(sessao -> sessao.getStatus().equals(ABERTA)).collect(toList()));
        LOGGER.info("Finalizando rotina de encerramento das sessões de votos.");
    }

    private void atualizarSessao(List<Sessao>  sessoesAberta) {
        LOGGER.info("atualizarSessao");
        sessoesAberta.stream().filter(sessao -> MINUTES.between(sessao.getDataHoraInicioSessao(), now()) == sessao.getMinutosSessao()).forEach(sessao -> {
            LOGGER.info("Encerrando sessão : inicio " + sessao.toString() );
            sessao.setStatus(ENCERRADA);
            List<Voto> votos = votoService.buscarTodos();
            sessao.setQuantidadeVotos(votos.size());
            sessao.setQuantidadeVotosSim(votos.stream().filter(voto -> voto.getVoto().equals(SIM)).count());
            sessao.setQuantidadeVotos(votos.stream().filter(voto -> voto.getVoto().equals(NAO)).count());
            sessao.setDataHoraInicioSessao(now());
            sessaoService.atualizarSessao(sessao);
            LOGGER.info("Encerrando sessão : fim " + sessao.toString() );
        });

    }

}
