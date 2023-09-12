package pl.tomwodz.nursery.domain.presence;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface PresenceRepository extends Repository<Presence, Long> {

    List<Presence> findAll(Sort sort);

    List<Presence> findAllByChild_GroupChildren_IdOrderByDataTimeEntryDesc(Long id);

  List<Presence> findAllByChild_GroupChildren_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(Long id, LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture);

  List<Presence> findAllByChild_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(Long id, LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture);

    List<Presence> findAllByChild_Id(Long id);

    Optional<Presence> findById(Long id);
    Presence save(Presence presence);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);

}
