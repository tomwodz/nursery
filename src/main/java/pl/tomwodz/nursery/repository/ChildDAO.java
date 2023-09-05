package pl.tomwodz.nursery.repository;

import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.Child;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildDAO {
    List<Child> findAll();
    Optional<Child> findById(Long id);
    Child save(Child child);
    void updateById(Long id, Child newChild);
    boolean existsById(Long id);
    void deleteById(Long id);

}
