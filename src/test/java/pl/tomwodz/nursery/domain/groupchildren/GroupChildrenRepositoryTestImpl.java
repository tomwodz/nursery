package pl.tomwodz.nursery.domain.groupchildren;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GroupChildrenRepositoryTestImpl implements GroupChildrenRepository{

    Map<Long, GroupChildren> inMemoryDatabase = new ConcurrentHashMap<>();
    Random random = new Random();
    @Override
    public List<GroupChildren> findAll() {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<GroupChildren> findById(Long id) {
        GroupChildren groupChildren = this.inMemoryDatabase.get(id);
        if(groupChildren == null){
            return Optional.empty();
        }
        return Optional.of(groupChildren);
    }

    @Override
    public Optional<GroupChildren> findByName(String name) {
        Optional<GroupChildren> groupChildren = this.inMemoryDatabase.values()
                .stream()
                .filter(gr -> gr.getName().equals(name))
                .findFirst();
        if(groupChildren.isEmpty()){
            return Optional.empty();
        }
        return groupChildren;
    }

    @Override
    public GroupChildren save(GroupChildren groupChildren) {
        if (groupChildren.getId() == null) {
            groupChildren.setId(random.nextLong());
        }
        this.inMemoryDatabase.put(groupChildren.getId(), groupChildren);
        return this.inMemoryDatabase.get(groupChildren.getId());
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
