package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.IUserDAO;
import pl.tomwodz.nursery.services.IUserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;
    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userDAO.findById(id);
    }
}
