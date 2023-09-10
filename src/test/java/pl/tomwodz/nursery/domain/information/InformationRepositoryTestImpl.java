package pl.tomwodz.nursery.domain.information;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class InformationRepositoryTestImpl implements InformationRepository {

    Map<Long, Information> inMemoryDatabase = new ConcurrentHashMap<>();
    Random random = new Random();

    @Override
    public List<Information> findAll(Sort sort) {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Information> findById(Long id) {
        Information information = this.inMemoryDatabase.get(id);
        if(information == null){
            return Optional.empty();
        }
        return Optional.of(information);
    }

    @Override
    public Information save(Information information) {
        if (information.getId() == null) {
            information.setId(random.nextLong());
        }
        this.inMemoryDatabase.put(information.getId(), information);
        return this.inMemoryDatabase.get(information.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return this.inMemoryDatabase.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        this.inMemoryDatabase.remove(id);
    }
}
