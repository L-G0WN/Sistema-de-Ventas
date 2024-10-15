package com.monagas.view.login.services;

import com.monagas.mysql.ConnectionTo;
import com.monagas.view.login.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public User auth(String username, String password) {
        String query = "SELECT username, password, firstname, lastname, account_type FROM users WHERE username = ?";

        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (storedPassword.equals(password)) {
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        int account_type = rs.getInt("account_type");
                        return new User(username, firstname, lastname, account_type);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(String username) {
        String query = "SELECT COUNT(*) AS count FROM Users WHERE username = ?";
        
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userCount = rs.getInt("count");
                    return userCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean question(String username, String question, String answer) {
        String query = "SELECT answer FROM Users WHERE username = ? AND question = ?";
        
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, question);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedAnswer = rs.getString("answer");
                    if (storedAnswer.equals(answer)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(String username, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE username = ?";
        
        try (Connection conn = ConnectionTo.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
