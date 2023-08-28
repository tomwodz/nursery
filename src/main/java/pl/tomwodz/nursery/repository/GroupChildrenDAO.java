package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;
import java.util.Optional;

public interface GroupChildrenDAO {
    List<GroupChildren> finAll();

    GroupChildren findById(Long id);

    Optional<GroupChildren> findByName(String name);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);

    void existsById(Long id);

    void deleteById(Long id);

}
