package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.domain.user.UserRepository;
import pl.tomwodz.nursery.infrastructure.user.controller.error.UserNotFoundException;
import pl.tomwodz.nursery.domain.child.Child;
import pl.tomwodz.nursery.domain.user.User;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements pl.tomwodz.nursery.services.UserService {

    private final UserRepository userDAO;
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
        return this.userDAO.findById(id).
                orElseThrow(()-> new UserNotFoundException("Nie znaleziono użytkownika o id: " + id));
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
        this.existsById(id);
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

    @Override
    public void existsById(Long id) {
        if (!this.userDAO.existsById(id)) {
            throw new UserNotFoundException("Nie znaleziono użytkownika o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.userDAO.existsById(id);
        this.userDAO.deleteById(id);
    }


}
