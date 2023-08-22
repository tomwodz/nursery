package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);

}
