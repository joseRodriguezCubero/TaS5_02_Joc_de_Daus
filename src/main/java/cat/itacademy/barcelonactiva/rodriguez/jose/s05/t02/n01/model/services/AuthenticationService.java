package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services;


import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SignUpRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SigninRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}