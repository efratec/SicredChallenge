package br.com.api.api.br.com.sicredi.api.service.impl;

import br.com.api.api.br.com.sicredi.api.model.CentralBank;
import br.com.api.api.br.com.sicredi.api.service.ReceitaService;
import br.com.api.api.br.com.sicredi.api.util.SimulaServicosCenarioUtil;
import org.springframework.stereotype.Service;

import static br.com.api.api.br.com.sicredi.api.model.Status.validarStatus;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Override
    public boolean atualizarConta(CentralBank centralBank) throws RuntimeException, InterruptedException {
        SimulaServicosCenarioUtil.simularCenarioDeErroNoServico();
        return atualizaConta(centralBank);
    }

    private boolean atualizaConta(CentralBank centralBank) {
        return centralBank.validarFormatoStringAgenciaEConta() && validarStatus(centralBank.getStatus().getDescription());
    }

}
