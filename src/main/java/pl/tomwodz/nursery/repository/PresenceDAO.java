package pl.tomwodz.nursery.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.Presence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface PresenceDAO {

    List<Presence> findAll(Sort sort);

    List<Presence> findAllByChild_GroupChildren_IdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);

    List<Presence> findAllByChild_IdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);

    List<Presence> findAllByChild_Id(Long id);

    List<Presence> findAllByChild_GroupChildren_Id(Long id);
    Optional<Presence> findFirstByChild_Id(Long id);
    Optional<Presence> findById(Long id);
    Presence save(Presence presence);

    boolean existsById(Long id);
    void deleteById(Long id);
    void updateById(Long id, Presence newPresence);
}
