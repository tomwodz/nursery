package pl.tomwodz.nursery.repository.dao.spingdata;

import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends Repository<User, Long> {

    List<User> findAll();
    Optional<User> findById(Long id);

}