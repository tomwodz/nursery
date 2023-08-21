package pl.tomwodz.nursery.repository.dao.spingdata;

import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;
import java.util.Optional;

public interface IGroupChildrenRepository extends Repository<GroupChildren, Long> {
    List<GroupChildren> findAll();
    Optional<GroupChildren> findById(Long id);
    GroupChildren save(GroupChildren groupChildren);
}
