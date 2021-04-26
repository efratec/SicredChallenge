package br.com.api.api.br.com.sicredi.api.service;

import br.com.api.api.br.com.sicredi.api.model.CentralBank;

public interface ReceitaService {

    boolean atualizarConta(CentralBank centralBank) throws RuntimeException, InterruptedException;

}
