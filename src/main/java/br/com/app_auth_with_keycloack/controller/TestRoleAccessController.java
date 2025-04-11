package br.com.app_auth_with_keycloack.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRoleAccessController {
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public String list(){
        return "Listando...";
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(){
        return "Criando...";
    }
}
