package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.UserDAO;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements pl.tomwodz.nursery.services.UserService {

    private final UserDAO userDAO;
    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userDAO.findById(id);
    }
}
