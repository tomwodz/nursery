package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();
    Optional<User> findById(Long id);

}
