package br.com.api.api.br.com.sicredi.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import static br.com.api.api.br.com.sicredi.api.util.Constantes.NUMBERS.QUATRO;
import static br.com.api.api.br.com.sicredi.api.util.Constantes.NUMBERS.SEIS;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class CentralBank {

    private String agencia;
    private String conta;
    private BigDecimal saldo;
    private Status status;

    private boolean validarFormatoConta() {
        return this.conta == null || this.conta.length() == SEIS;
    }

    private boolean validarFormatoAgencia() {
        return this.agencia == null || this.agencia.length() == QUATRO;
    }

    public boolean validarFormatoStringAgenciaEConta() {
        return validarFormatoAgencia() && validarFormatoConta();
    }

}
