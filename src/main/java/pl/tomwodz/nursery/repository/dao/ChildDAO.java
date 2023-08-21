package pl.tomwodz.nursery.repository.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.controllers.errors.ChildNotFoundException;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.repository.IChildDAO;
import pl.tomwodz.nursery.repository.dao.springdata.IChildRepository;

import java.util.List;

@AllArgsConstructor
@Repository
public class ChildDAO implements IChildDAO {

    private final IChildRepository childRepository;
    @Override
    public List<Child> findAll() {
        return this.childRepository.findAll();
    }

    @Override
    public Child findById(Long id) {
        return this.childRepository.findById(id)
                .orElseThrow(()-> new ChildNotFoundException("Nie znaleziono dziecka o id: " + id));
    }
}
