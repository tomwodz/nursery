package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    List<User> findByRole(User.Role role);
    User findById(Long id);
    Optional<User> findByLogin(String login);
    User save(User user);
    void updateById(Long id, User newUser);
    void existsById(Long id);
    void deleteById(Long id);
    boolean existsByLogin(String login);
}
