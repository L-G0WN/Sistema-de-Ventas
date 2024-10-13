package com.monagas.view.login.components;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class CredentialManager {

    private static final String PROPERTIES_FILE_NAME = "application.properties";
    private static final String USER_HOME_DIR = System.getProperty("user.home");
    private static final String PROPERTIES_FILE_PATH = USER_HOME_DIR + File.separator + PROPERTIES_FILE_NAME;
    private static final String ENCRYPTION_KEY = "126B0801299FB150C70DA8F6CD4296AD";

    static {
        createPropertiesFileIfNotExists();
    }

    private static void createPropertiesFileIfNotExists() {
        File propertiesFile = new File(PROPERTIES_FILE_PATH);
        if (!propertiesFile.exists()) {
            try {
                propertiesFile.createNewFile();
                try (OutputStream output = new FileOutputStream(propertiesFile)) {
                    Properties defaultProperties = new Properties();
                    defaultProperties.store(output, null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveCredentials(String username) {
        Properties properties = new Properties();
        try {
            String encryptedUsername = encrypt(username, ENCRYPTION_KEY);
            properties.setProperty("username", encryptedUsername);

            try (OutputStream output = new FileOutputStream(PROPERTIES_FILE_PATH)) {
                properties.store(output, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties loadCredentials() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(input);
            String encryptedUsername = properties.getProperty("username");
            if (encryptedUsername != null) {
                String decryptedUsername = decrypt(encryptedUsername, ENCRYPTION_KEY);
                properties.setProperty("username",decryptedUsername);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static void clearCredentials() {
        Properties properties = new Properties();

        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE_PATH)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
