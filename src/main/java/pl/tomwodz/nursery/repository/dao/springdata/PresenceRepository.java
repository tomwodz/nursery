package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.model.Presence;

import java.util.List;
import java.util.Optional;

public interface PresenceRepository extends Repository<Presence, Long> {

    List<Presence> findAll(Sort sort);

    Optional<Presence> findById(Long id);
    Presence save(Presence presence);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Presence p SET p.child = :#{#newPresence.child}, p.day = :#{#newPresence.day}, " +
            "p.timeEntry = :#{#newPresence.timeEntry}, p.timeDeparture = :#{#newPresence.timeDeparture} WHERE p.id = :id")
    void updateById(Long id, Presence newPresence);
}
