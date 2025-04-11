package br.com.app_auth_with_keycloack.dto;

public record AuthUserRequest (String password, String clientId, String grantType, String user) {}
