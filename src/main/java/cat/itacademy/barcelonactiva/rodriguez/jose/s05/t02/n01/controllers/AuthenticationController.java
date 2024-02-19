package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SignUpRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SigninRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}