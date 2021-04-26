package br.com.api.api.br.com.sicredi.api.util;

import java.io.FileOutputStream;

public class GeradorCsvUtil {

    public static void gerarCSV(StringBuffer stringBuffer) {
        try {
            FileOutputStream arquivo = new FileOutputStream("retorno.csv");
            byte[] contentInBytes = stringBuffer.toString().getBytes();
            arquivo.write(contentInBytes);
            arquivo.flush();
            if (arquivo != null) {
                arquivo.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
