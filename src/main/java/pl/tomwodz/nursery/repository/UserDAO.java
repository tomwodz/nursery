package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    User findById(Long id);
    Optional<User> findByLogin(String login);
    User save(User user);
}
