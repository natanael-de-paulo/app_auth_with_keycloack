package br.com.app_auth_with_keycloack.controller;
import br.com.app_auth_with_keycloack.dto.AuthUserRequest;
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

    @PostMapping("/")
    public ResponseEntity<?> auth(@RequestBody AuthUserRequest user) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.clientId());
        formData.add("username", user.user());
        formData.add("password", user.password());
        formData.add("grant_type", user.grantType());


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        var result = restTemplate.postForEntity("http://localhost:8080/realms/youtube/protocol/openid-connect/token", entity, String.class);
        return ResponseEntity.ok().body(result.getBody());
    };

}
