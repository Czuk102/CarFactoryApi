package com.portfolio.carfactoryapi.controler;

import com.portfolio.carfactoryapi.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TokenController {
    private static final Logger LOG = LoggerFactory.getLogger(TokenController.class);

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        LOG.info("Token for user: {}",authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.info("Token granted {}", token);
        return token;
    }
}
