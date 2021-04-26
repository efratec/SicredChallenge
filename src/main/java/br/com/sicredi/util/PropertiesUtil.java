package br.com.sicredi.util;

public class PropertiesUtil {

    public static String getProperties(){
        if (System.getProperty("os.name").contains("Windows")) {
            return System.getProperty("user.home") + "\\";
        } else {
            return System.getProperty("user.home") + "/";
        }
    }

}
