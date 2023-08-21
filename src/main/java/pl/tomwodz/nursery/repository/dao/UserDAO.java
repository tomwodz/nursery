package pl.tomwodz.nursery.repository.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.UserNotFoundException;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.IUserDAO;
import pl.tomwodz.nursery.repository.dao.springdata.IUserRepository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserDAO implements IUserDAO {

    private final IUserRepository userRepository;

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
