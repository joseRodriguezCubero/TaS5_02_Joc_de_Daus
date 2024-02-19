package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.impl;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SignUpRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.request.SigninRequest;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.User;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql.UserRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.role.Role;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.AuthenticationService;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}