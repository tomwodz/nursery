package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.exception.GroupChildrenNotFoundException;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.repository.GroupChildrenDAO;
import pl.tomwodz.nursery.repository.dao.springdata.GroupChildrenRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Transactional
public class GroupChildrenDAOImpl implements GroupChildrenDAO {

    private final GroupChildrenRepository groupChildrenRepository;

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
    public Optional<GroupChildren> findByName(String name) {
        return this.groupChildrenRepository.findByName(name);
    }

    @Override
    public GroupChildren save(GroupChildren groupChildren) {
        return this.groupChildrenRepository.save(groupChildren);
    }

    @Override
    public void updateById(Long id, GroupChildren newGroupChildren) {
        this.existsById(id);
        this.groupChildrenRepository.updateById(id, newGroupChildren);
    }

    @Override
    public void existsById(Long id) {
        if(!groupChildrenRepository.existsById(id)){
            throw new GroupChildrenNotFoundException("Nie znaleziono grupy dzieci o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.groupChildrenRepository.deleteById(id);
    }
}
