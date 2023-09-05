package pl.tomwodz.nursery.repository;

import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupChildrenDAO {
    List<GroupChildren> findAll();

    Optional<GroupChildren> findById(Long id);

    Optional<GroupChildren> findByName(String name);

    GroupChildren save(GroupChildren groupChildren);

    void updateById(Long id, GroupChildren newGroupChildren);

    boolean existsById(Long id);

    void deleteById(Long id);

}
