package br.com.api.api.br.com.sicredi.api;

import br.com.api.api.br.com.sicredi.api.service.SincronizacaoReceita;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] arquivos) {
        ApplicationContext context = SpringApplication.run(ApiApplication.class, arquivos);
        Arrays.stream(arquivos).forEach(arquivo -> {
            SincronizacaoReceita sincronizacaoReceita = (SincronizacaoReceita) context.getBean("sincronizar");
            sincronizacaoReceita.verificarArquivo(arquivo);
        });
    }

}
