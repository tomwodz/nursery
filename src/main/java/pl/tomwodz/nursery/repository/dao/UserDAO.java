package pl.tomwodz.nursery.repository.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.UserNotFoundException;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.dao.springdata.UserRepository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserDAO implements pl.tomwodz.nursery.repository.UserDAO {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {

        return this.userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("Nie znaleziono użytkownika o id: " + id));
    }
}
