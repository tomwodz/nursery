package pl.tomwodz.nursery.domain.user;

import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryTestImpl implements UserRepository{

    Map<Long, User> inMemoryDatabase = new ConcurrentHashMap<>();

    Random random = new Random();
    @Override
    public List<User> findAll() {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public List<User> findByRole(User.Role role) {
        return this.inMemoryDatabase.values()
                .stream()
                .filter(user -> user.getRole().equals(role))
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = this.inMemoryDatabase.get(id);
        if(user == null){
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(random.nextLong());
        }
        this.inMemoryDatabase.put(user.getId(), user);
        return this.inMemoryDatabase.get(user.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return this.inMemoryDatabase.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
            this.inMemoryDatabase.remove(id);
    }

    @Override
    public boolean existsByLogin(String login) {
        return this.inMemoryDatabase.values()
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst().isPresent();
    }

    @Override
    public Optional<User> findFirstByChild_Id(Long id) {
       return  null;
    }
}
