package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.UserNotFoundException;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.UserDAO;
import pl.tomwodz.nursery.repository.dao.springdata.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> findByRole(User.Role role) {
        return this.userRepository.findByRole(role);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("Nie znaleziono użytkownika o id: " + id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void updateById(Long id, User newUser) {
        this.userRepository.updateById(id, newUser);
    }

    @Override
    public void existsById(Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException("Nie znaleziono użytkownika o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.userRepository.deleteById(id);
    }

}
