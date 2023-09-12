package pl.tomwodz.nursery.domain.authentication;

import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.domain.user.User;

import java.util.Optional;

interface AuthenticationRepository extends Repository<User, Long> {
    Optional<User> findByLogin(String login);

}
