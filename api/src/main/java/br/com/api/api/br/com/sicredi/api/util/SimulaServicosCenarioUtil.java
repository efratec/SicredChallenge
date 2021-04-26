package br.com.api.api.br.com.sicredi.api.util;

import static br.com.api.api.br.com.sicredi.api.util.Constantes.Strings.ERROR;

public class SimulaServicosCenarioUtil {

    public static void simularCenarioDeErroNoServico() throws InterruptedException {
        simularTempoDeRespostaDoServicoEntreUmECincoSegundos();
        lancarExcecaoDeErroSeNumeroForIgual500();
    }

    private static void simularTempoDeRespostaDoServicoEntreUmECincoSegundos() throws InterruptedException {
        long wait = Math.round(Math.random() * 4000) + 1000;
        Thread.sleep(wait);
    }

    private static void lancarExcecaoDeErroSeNumeroForIgual500() {
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) throw new RuntimeException(ERROR);
    }

}
