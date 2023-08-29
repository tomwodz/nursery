package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.repository.ChildDAO;

import java.util.List;


@Service
@AllArgsConstructor
public class ChildServiceImpl implements pl.tomwodz.nursery.services.ChildService {

    private final ChildDAO childDAO;
    @Override
    public List<Child> findAll() {
        return this.childDAO.findAll();
    }

    @Override
    public Child findById(Long id) {
        return this.childDAO.findById(id);
    }

    @Override
    public Child save(Child child) {
        return this.childDAO.save(child);
    }

    @Override
    public void updateById(Long id, Child newChild) {
        this.childDAO.updateById(id, newChild);
    }

    @Override
    public void existsById(Long id) {
        this.childDAO.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.childDAO.deleteById(id);
    }
}
