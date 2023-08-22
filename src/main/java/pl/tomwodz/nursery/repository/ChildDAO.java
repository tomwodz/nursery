package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.Child;

import java.util.List;

public interface ChildDAO {
    List<Child> findAll();
    Child findById(Long id);
    void existsById(Long id);
    Child save(Child child);
    void updateById(Long id, Child newChild);

}
