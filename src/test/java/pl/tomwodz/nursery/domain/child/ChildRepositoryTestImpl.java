package pl.tomwodz.nursery.domain.child;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ChildRepositoryTestImpl implements ChildRepository {

    Map<Long, Child> inMemoryDatabase = new ConcurrentHashMap<>();

    Random random = new Random();

    @Override
    public List<Child> findAll(Sort sort) {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public List<Child> findAll() {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Child> findById(Long id) {
        Child child = this.inMemoryDatabase.get(id);
        if(child == null){
            return Optional.empty();
        }
        return Optional.of(child);
    }

    @Override
    public Child save(Child child) {
        if (child.getId() == null) {
            child.setId(random.nextLong());
        }
        this.inMemoryDatabase.put(child.getId(), child);
        return this.inMemoryDatabase.get(child.getId());
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
    public List<Child> findAllByGroupChildren_Id(Long id) {
        return this.inMemoryDatabase.values()
                .stream()
                .filter(child -> child.getGroupChildren().getId() == id)
                .toList();
    }


    @Override
    public List<Child> findAllByParent_Id(Long id) {
        return this.inMemoryDatabase.values()
                .stream()
                .filter(child -> child.getParent().getId() == id)
                .toList();
    }
}
