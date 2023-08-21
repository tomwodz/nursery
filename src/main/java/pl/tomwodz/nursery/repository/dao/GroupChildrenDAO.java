package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.GroupChildrenNotFoundException;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.repository.IGroupChildrenDAO;
import pl.tomwodz.nursery.repository.dao.springdata.IGroupChildrenRepository;

import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class GroupChildrenDAO implements IGroupChildrenDAO {

    private final IGroupChildrenRepository groupChildrenRepository;

    @Override
    public List<GroupChildren> finAll() {
        return groupChildrenRepository.findAll();
    }

    @Override
    public GroupChildren findById(Long id) {

        return this.groupChildrenRepository.findById(id).
                orElseThrow(()-> new GroupChildrenNotFoundException("Nie znaleziono grupy dzieci o id: " + id));
    }

    @Override
    public GroupChildren save(GroupChildren groupChildren) {
        return this.groupChildrenRepository.save(groupChildren);
    }

    @Override
    public void updateById(Long id, GroupChildren newGroupChildren) {
        this.groupChildrenRepository.updateById(id, newGroupChildren);
    }
}
