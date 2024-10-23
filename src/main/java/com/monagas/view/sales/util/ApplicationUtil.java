package com.monagas.view.sales.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationUtil {

    public static String getVersion() {
        Properties properties = new Properties();
        try ( InputStream input = ApplicationUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                return "0";
            }
            properties.load(input);
            return properties.getProperty("application.version", "0");
        } catch (IOException ex) {
            return "0";
        }
    }
}
