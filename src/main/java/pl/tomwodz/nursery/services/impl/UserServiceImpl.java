package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.UserDAO;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements pl.tomwodz.nursery.services.UserService {

    private final UserDAO userDAO;
    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userDAO.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return this.userDAO.findByLogin(login);
    }

    @Override
    public User save(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return this.userDAO.save(user);
    }
}
