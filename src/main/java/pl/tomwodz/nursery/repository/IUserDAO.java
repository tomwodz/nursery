package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User> findAll();
    Optional<User> findById(Long id);
}
