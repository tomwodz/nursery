package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    List<User> findByRole(User.Role role);
    User findById(Long id);
    Optional<User> findByLogin(String login);
    User save(User user);
    void updateById(Long id, User newUser);

    void changeActiveById(Long id, User userToExchangeActive);

    void existsById(Long id);
    void deleteById(Long id);


}
