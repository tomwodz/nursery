package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.repository.GroupChildrenDAO;

import java.util.List;

@AllArgsConstructor
@Service
public class GroupChildrenServiceImpl implements pl.tomwodz.nursery.services.GroupChildrenService {

    private final GroupChildrenDAO groupChildrenDAO;
    @Override
    public List<GroupChildren> findAll() {
        return this.groupChildrenDAO.finAll();
    }

    @Override
    public GroupChildren findById(Long id) {
        return this.groupChildrenDAO.findById(id);
    }

    @Override
    public GroupChildren save(GroupChildren groupChildren) {
        return this.groupChildrenDAO.save(groupChildren);
    }

    @Override
    public void updateById(Long id, GroupChildren newGroupChildren) {
        this.groupChildrenDAO.updateById(id,newGroupChildren);
    }
}
