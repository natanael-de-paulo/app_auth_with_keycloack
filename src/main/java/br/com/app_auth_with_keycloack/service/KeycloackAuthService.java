package br.com.app_auth_with_keycloack.service;
import br.com.app_auth_with_keycloack.dto.AuthUserRequest;
import br.com.app_auth_with_keycloack.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class KeycloackAuthService implements IAuthService {
    private final RestTemplate restTemplate;

    @Value("${auth.keycloak.url}")
    private String keycloakUrl;

    public KeycloackAuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String authenticate(AuthUserRequest user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.clientId());
        formData.add("username", user.user());
        formData.add("password", user.password());
        formData.add("grant_type", user.grantType());


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        var result = this.restTemplate.postForEntity(this.keycloakUrl, entity, String.class);
        return result.getBody();
    }
}

