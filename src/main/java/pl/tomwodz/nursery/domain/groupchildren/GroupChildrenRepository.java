package pl.tomwodz.nursery.domain.groupchildren;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GroupChildrenRepository extends Repository<GroupChildren, Long> {
    List<GroupChildren> findAll();
    Optional<GroupChildren> findById(Long id);
    Optional<GroupChildren> findByName(String name);
    GroupChildren save(GroupChildren groupChildren);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);
}
