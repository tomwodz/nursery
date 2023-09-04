package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.Presence;

import java.time.LocalDate;
import java.util.List;

public interface PresenceService {

    List<Presence> findAll();
    List<Presence> findAllByChildIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);
    List<Presence> findAllByGroupChildrenIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo);
    List<Presence> findAllByChildId(Long id);

    Presence findById(Long id);
    Presence save(Presence presence);
    void existsById(Long id);
    void deleteById(Long id);

    void updateById(Long id, Presence newPresence);

}
