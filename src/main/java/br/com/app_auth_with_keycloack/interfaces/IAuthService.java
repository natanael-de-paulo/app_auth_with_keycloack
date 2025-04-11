package br.com.app_auth_with_keycloack.interfaces;
import br.com.app_auth_with_keycloack.dto.AuthUserRequest;

public interface IAuthService {
    String authenticate(AuthUserRequest user);
}