package br.com.api.api.br.com.sicredi.api.service.impl;

import br.com.api.api.br.com.sicredi.api.model.CentralBank;
import br.com.api.api.br.com.sicredi.api.model.Status;
import br.com.api.api.br.com.sicredi.api.service.ReceitaService;
import br.com.api.api.br.com.sicredi.api.service.SincronizacaoReceita;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

import static br.com.api.api.br.com.sicredi.api.util.Constantes.Strings.*;
import static br.com.api.api.br.com.sicredi.api.util.GeradorCsvUtil.gerarCSV;

@Slf4j
@Component("sincronizar")
@Service
public class SincronizacaoReceitaImpl implements SincronizacaoReceita {

    @Autowired
    private ReceitaService receitaService;

    @Override
    public void verificarArquivo(String arquivo) {

        log.info("Iniciando a Verificação do arquivo....");

        StringBuffer stringCSV = new StringBuffer();
        stringCSV.append(obterCabecalho());

        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader reader = new BufferedReader(fileReader);
            Stream<String> linhas = reader.lines();

            linhas.forEach(linha -> {
                String[] divisao = linha.split(DIVISAO);
                obterArquivoTratado(receitaService, stringCSV, divisao);
            });

            gerarCSV(stringCSV);
            fileReader.close();
            reader.close();

        } catch (FileNotFoundException e) {
            log.info("Erro ao atualizar o arquivo, favor verificar!", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Sincronização concluida com sucesso!");
    }

    private static String obterCabecalho() {
        return "agencia;conta;saldo;status;atualizado\n";
    }

    private static void obterArquivoTratado(ReceitaService receitaService, StringBuffer stringCSV, String[] divisao) {
        if (!AGENCIA.equals(divisao[0])) {
            CentralBank centralBank = criarCentralBankPorLinhaDoArquivo(divisao);
            try {
                stringCSV.append(centralBank.getAgencia());
                stringCSV.append(DIVISAO);
                stringCSV.append(centralBank.getConta());
                stringCSV.append(DIVISAO);
                stringCSV.append(centralBank.getSaldo());
                stringCSV.append(DIVISAO);
                stringCSV.append(receitaService.atualizarConta(centralBank));
                stringCSV.append(QUEBRA_LINHA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static CentralBank criarCentralBankPorLinhaDoArquivo(String[] split) {
        return CentralBank.builder()
                .agencia(split[0])
                .conta(split[1].replace("-", ""))
                .saldo(new BigDecimal(split[2].replace(",", ".")))
                .status(Status.obterStatusPorDescricao(split[3]))
                .build();
    }

}
