package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;
import java.util.Optional;

public interface GroupChildrenRepository extends Repository<GroupChildren, Long> {
    List<GroupChildren> findAll();
    Optional<GroupChildren> findById(Long id);
    Optional<GroupChildren> findByName(String name);
    GroupChildren save(GroupChildren groupChildren);

    @Modifying
    @Query("UPDATE GroupChildren g SET g.name = :#{#newGroupChildren.name} WHERE g.id = :id")
    void updateById(Long id, GroupChildren newGroupChildren);
}
