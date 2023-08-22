package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;

public interface GroupChildrenService {
    List<GroupChildren> findAll();

    GroupChildren findById(Long id);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);
}
