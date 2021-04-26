package br.com.api.api.br.com.sicredi.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {

    A("A"),
    I("I"),
    B("B"),
    P("P");

    private String description;

    public static Boolean validarStatus(String status) {
        return status == null || isContainsInList(status);
    }

    private static boolean isContainsInList(String status) {
        return Arrays.stream(Status.values()).anyMatch(c -> c.getDescription().equals(status));
    }

    public static Status obterStatusPorDescricao(String descricao) {
        return Status.valueOf(descricao);
    }

}
