package com.monagas.view.login.services;

import com.monagas.mysql.ConnectionTo;
import com.monagas.view.login.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class UserService {

    private static final String ENCRYPTION_KEY = "126B0801299FB150C70DA8F6CD4296AD";

    public static User verifyLogin(String username, String password) {
        String selectUserSQL = "SELECT username, password, firstname, lastname, account_type FROM Users WHERE username = ?";
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (password.equals(storedPassword)) {
//                    if (decrypt(storedPassword, ENCRYPTION_KEY).equals(password)) {
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        int account_type = rs.getInt("account_type");
                        return new User(username, firstname, lastname, account_type);
//                    }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean userExists(String username) {
        String selectUserSQL = "SELECT COUNT(*) AS userCount FROM Users WHERE username = ?";
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userCount = rs.getInt("userCount");
                    return userCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifyQuestion(String username, String question, String answer) {
        String selectSecuritySQL = "SELECT answer FROM Users WHERE username = ? AND question = ?";
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSecuritySQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, question);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedAnswer = rs.getString("answer");
                    return true;
                    //return decrypt(storedAnswer, ENCRYPTION_KEY).equalsIgnoreCase(answer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean changePassword(String username, String newPassword) {
        String updatePasswordSQL = "UPDATE Users SET password = ? WHERE username = ?";
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updatePasswordSQL)) {
            pstmt.setString(1, encrypt(newPassword, ENCRYPTION_KEY));
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String encrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
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
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
