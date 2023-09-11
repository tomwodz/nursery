package pl.tomwodz.nursery.domain.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAll();

    List<User> findByRole(User.Role role);

    Optional<User> findById(Long id);
    User save(User user);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);
    boolean existsByLogin(String login);

    Optional<User> findFirstByChild_Id(Long id);

}