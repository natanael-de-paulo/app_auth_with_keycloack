package br.com.app_auth_with_keycloack.controller;
import br.com.app_auth_with_keycloack.dto.AuthUserRequest;
import br.com.app_auth_with_keycloack.interfaces.IAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthUserRequest user) {
        String token = authService.authenticate(user);
        return ResponseEntity.ok().body(token);
    };

}
