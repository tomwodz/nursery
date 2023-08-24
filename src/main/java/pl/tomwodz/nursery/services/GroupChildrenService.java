package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;
import java.util.Optional;

public interface GroupChildrenService {
    List<GroupChildren> findAll();

    GroupChildren findById(Long id);

    Optional<GroupChildren> findByName(String name);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);
}
