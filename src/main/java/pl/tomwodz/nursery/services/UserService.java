package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.User;

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
    boolean existsByLogin(String login);
    void deleteById(Long id);

    boolean checkExistenceOfParentChildRelationship(Long id, User user);

}
