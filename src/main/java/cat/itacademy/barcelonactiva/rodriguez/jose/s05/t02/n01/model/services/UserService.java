package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}