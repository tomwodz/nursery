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
    public List<User> findByRole(User.Role role) {
        return this.userDAO.findByRole(role);
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
        if(user.getPassword()!=null){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));}
        return this.userDAO.save(user);
    }

    @Override
    public void updateById(Long id, User newUser) {
        this.userDAO.updateById(id, newUser);
    }

    @Override
    public void changeActiveById(Long id, User userToChangeActive) {
        User userWithChangeActive = userToChangeActive;
        if(userToChangeActive.isActive() &&
                userToChangeActive.getRole()!= User.Role.ADMIN){
            userWithChangeActive.setActive(false);
        } else {
            userWithChangeActive.setActive(true);
        }
        this.updateById(id, userWithChangeActive);
    }
}
