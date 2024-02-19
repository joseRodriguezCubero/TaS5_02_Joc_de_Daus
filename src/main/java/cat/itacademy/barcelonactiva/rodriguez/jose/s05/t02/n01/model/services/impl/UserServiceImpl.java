package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql.UserRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
            }
        };
    }
}