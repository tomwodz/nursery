package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.Child;


import java.util.List;
import java.util.Optional;

public interface ChildRepository extends Repository<Child, Long> {

    List<Child> findAll();
    Optional<Child> findById(Long id);

    Child save(Child Child);

    boolean existsById(Long id);

    @Modifying
    @Query("DELETE FROM Child c WHERE c.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Child c SET c.name = :#{#newChild.name}, c.surname = :#{#newChild.surname}, c.dayOfBirth = :#{#newChild.dayOfBirth}, c.groupChildren = :#{#newChild.groupChildren}, c.parent = :#{#newChild.parent}  WHERE c.id = :id")
    void updateById(Long id, Child newChild);

}