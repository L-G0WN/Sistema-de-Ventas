package com.monagas.view.login.models;

public class User {
    
    private String username;
    private String name;
    private String lastname;
    private int account_type;
    
    public User(String username, String name, String lastname, int account_type) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.account_type = account_type;
    }
    
    public User(String username, String name, String lastname) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAcount_type() {
        return account_type;
    }

    public void setAcount_type(int acount_type) {
        this.account_type = acount_type;
    }
}
