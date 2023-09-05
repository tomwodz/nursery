package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.exception.GroupChildrenNotFoundException;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.repository.GroupChildrenDAO;
import pl.tomwodz.nursery.services.GroupChildrenService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class GroupChildrenServiceImpl implements GroupChildrenService {

    private final GroupChildrenDAO groupChildrenDAO;
    @Override
    public List<GroupChildren> findAll() {
        return this.groupChildrenDAO.findAll();
    }

    @Override
    public GroupChildren findById(Long id) {
        return this.groupChildrenDAO.findById(id).
                orElseThrow(()-> new GroupChildrenNotFoundException("Nie znaleziono grupy dzieci o id: " + id));
    }

    @Override
    public Optional<GroupChildren> findByName(String name) {
        return this.groupChildrenDAO.findByName(name);
    }

    @Override
    public GroupChildren save(GroupChildren groupChildren) {
        return this.groupChildrenDAO.save(groupChildren);
    }

    @Override
    public void existsById(Long id) {
        if(!groupChildrenDAO.existsById(id)){
            throw new GroupChildrenNotFoundException("Nie znaleziono grupy dzieci o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.groupChildrenDAO.deleteById(id);
    }

    @Override
    public void updateById(Long id, GroupChildren newGroupChildren) {
        this.existsById(id);
        this.groupChildrenDAO.updateById(id,newGroupChildren);
    }

    @Override
    public GroupChildren getGroupChildrenByNewChild(){
        Optional<GroupChildren> groupChildrenBox = this.findByName("Rekrutacja " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
        if(groupChildrenBox.isPresent()){
            return groupChildrenBox.get();
        } else {
            GroupChildren groupChildren = new GroupChildren("Rekrutacja " +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
            GroupChildren groupChildrenSaved = this.save(groupChildren);
            return groupChildrenSaved;
        }
    }
}
