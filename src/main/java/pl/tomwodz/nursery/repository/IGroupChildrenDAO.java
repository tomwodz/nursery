package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;

import java.util.List;
import java.util.Optional;

public interface IGroupChildrenDAO {
    List<GroupChildren> finAll();

    Optional<GroupChildren> findById(Long id);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);
}
