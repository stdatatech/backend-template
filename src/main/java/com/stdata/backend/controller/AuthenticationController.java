package com.stdata.backend.controller;

import com.stdata.backend.entity.User;
import com.stdata.backend.model.RestResult;
import com.stdata.backend.model.request.ReqLogin;
import com.stdata.backend.repository.UserRepository;
import com.stdata.backend.security.TokenProvider;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository users;

    @PostMapping("login")
    public RestResult login(@RequestBody ReqLogin request) {

        try {
            String username = request.getUsername();
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            Optional<User> optional = users.findByUsername(username);
            if (!optional.isPresent()) {
                throw new UsernameNotFoundException("user " + username + " is not found");
            }
            String token = tokenProvider.createToken(username, Collections.emptyList());
            return RestResult.success(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("logout")
    public RestResult logout(@RequestHeader(value = "Authorization") String authorization) {
        String jwtToken = authorization.substring("Bearer ".length());
        tokenProvider.invalidToken(jwtToken);
        return RestResult.success();
    }

}
