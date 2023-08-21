package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.repository.IGroupChildrenDAO;
import pl.tomwodz.nursery.services.IGroupChildrenService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupChildrenServiceImpl implements IGroupChildrenService {

    private final IGroupChildrenDAO groupChildrenDAO;
    @Override
    public List<GroupChildren> findAll() {
        return this.groupChildrenDAO.finAll();
    }

    @Override
    public Optional<GroupChildren> findById(Long id) {
        return this.groupChildrenDAO.findById(id);
    }
}
