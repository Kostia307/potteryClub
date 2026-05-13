package com.example.potteryclub;

public class AuthLoginResponse {
    private String token;
    private Long id;
    private boolean admin;

    public AuthLoginResponse(String token, Long id, boolean admin) {
        this.token = token;
        this.id = id;
        this.admin = admin;
    }

    public String getToken() { return token; }
    public Long getId() { return id; }
    public boolean isAdmin() { return admin; }
    public void setToken(String token) { this.token = token; }
    public void setId(Long id) { this.id = id; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}