package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAll();

    List<User> findByRole(User.Role role);

    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    User save(User user);
    @Modifying
    @Query("UPDATE User u SET u.name = :#{#newUser.name}, u.surname = :#{#newUser.surname}, u.email = :#{#newUser.email}, u.phoneNumber = :#{#newUser.phoneNumber}, u.role = :#{#newUser.role} WHERE u.id = :id")
    void updateById(Long id, User newUser);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);

}