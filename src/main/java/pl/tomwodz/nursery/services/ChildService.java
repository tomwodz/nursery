package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.Child;

import java.util.List;

public interface ChildService {
    List<Child> findAll();

    Child findById(Long id);

    Child save(Child child);

    void updateById(Long id, Child newChild);

    void existsById(Long id);
    void deleteById(Long id);

}
