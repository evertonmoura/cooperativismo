package com.cooperativismo.impl.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class VerificaSessao {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Scheduled(fixedDelay = 60000)
    public void verificaPorHora() {
        System.out.println(LocalDateTime.now());
    }
}
