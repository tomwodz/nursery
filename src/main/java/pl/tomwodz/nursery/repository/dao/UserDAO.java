package pl.tomwodz.nursery.repository.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.IUserDAO;
import pl.tomwodz.nursery.repository.dao.spingdata.IUserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserDAO implements IUserDAO {

    private final IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
