package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.Presence;
import pl.tomwodz.nursery.repository.PresenceDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PresenceRepository extends PresenceDAO, Repository<Presence, Long> {

    List<Presence> findAll(Sort sort);


    List<Presence> findAllByChild_GroupChildren_IdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);

    List<Presence> findAllByChild_IdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);

    List<Presence> findAllByChild_GroupChildren_Id(Long id);

    List<Presence> findAllByChild_Id(Long id);

    Optional<Presence> findFirstByChild_Id(Long id);

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
