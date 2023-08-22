package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.ChildNotFoundException;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.repository.dao.springdata.ChildRepository;

import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class ChildDAO implements pl.tomwodz.nursery.repository.ChildDAO {

    private final ChildRepository childRepository;
    @Override
    public List<Child> findAll() {
        return this.childRepository.findAll();
    }

    @Override
    public Child findById(Long id) {
        return this.childRepository.findById(id)
                .orElseThrow(()-> new ChildNotFoundException("Nie znaleziono dziecka o id: " + id));
    }

    @Override
    public void existsById(Long id) {
        if (!childRepository.existsById(id)) {
            throw new ChildNotFoundException("Nie znaleziono dziecka o id: " + id);
        }
    }

    @Override
    public Child save(Child child) {
        return this.childRepository.save(child);
    }

    @Override
    public void updateById(Long id, Child newChild) {
        this.existsById(id);
        this.childRepository.updateById(id, newChild);
    }
}
