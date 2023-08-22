package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(Long id);
}
