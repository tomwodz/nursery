package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;

public interface GroupChildrenDAO {
    List<GroupChildren> finAll();

    GroupChildren findById(Long id);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);
}
