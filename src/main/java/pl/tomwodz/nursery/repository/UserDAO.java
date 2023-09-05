package pl.tomwodz.nursery.repository;

import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserDAO {
    List<User> findAll();
    List<User> findByRole(User.Role role);
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    User save(User user);
    void updateById(Long id, User newUser);
    boolean existsById(Long id);
    void deleteById(Long id);
    boolean existsByLogin(String login);
}
