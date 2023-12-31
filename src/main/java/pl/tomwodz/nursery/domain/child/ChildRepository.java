package pl.tomwodz.nursery.domain.child;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface ChildRepository extends Repository<Child, Long> {

    List<Child> findAll(Sort sort);
    List<Child> findAll();
    Optional<Child> findById(Long id);

    Child save(Child Child);

    boolean existsById(Long id);

    @Modifying
    void deleteById(Long id);

    List<Child> findAllByGroupChildren_Id(Long id);

    List<Child> findAllByParent_Id(Long id);

}