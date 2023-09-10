package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.domain.child.ChildRepository;
import pl.tomwodz.nursery.infrastructure.child.controller.error.ChildNotFoundException;
import pl.tomwodz.nursery.model.Child;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ChildServiceImpl implements pl.tomwodz.nursery.services.ChildService {

    private final ChildRepository childDAO;
    @Override
    public List<Child> findAll() {
        return this.childDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Child findById(Long id) {
        return this.childDAO.findById(id).
                orElseThrow(()-> new ChildNotFoundException("Nie znaleziono dziecka o id: " + id));
    }

    @Override
    public Child save(Child child) {
        return this.childDAO.save(child);
    }

    @Override
    public void existsById(Long id) {
        if (!childDAO.existsById(id)) {
            throw new ChildNotFoundException("Nie znaleziono dziecka o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.childDAO.deleteById(id);
    }

}
