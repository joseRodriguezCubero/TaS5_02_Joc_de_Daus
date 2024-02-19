package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}