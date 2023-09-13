package pl.tomwodz.nursery.domain.presence;

import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PresenceRepositoryTestImpl implements PresenceRepository {

    Map<Long, Presence> inMemoryDatabase = new ConcurrentHashMap<>();

    Random random = new Random();
    @Override
    public List<Presence> findAll(Sort sort) {
        return this.inMemoryDatabase.values()
                .stream()
                .toList();
    }

    @Override
    public Optional<Presence> findById(Long id) {
        Presence presence = this.inMemoryDatabase.get(id);
        if(presence == null){
            return Optional.empty();
        }
        return Optional.of(presence);
    }

    @Override
    public Presence save(Presence presence) {
        if (presence.getId() == null) {
            presence.setId(random.nextLong());
        }
        this.inMemoryDatabase.put(presence.getId(), presence);
        return this.inMemoryDatabase.get(presence.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return this.inMemoryDatabase.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        this.inMemoryDatabase.remove(id);    }

    @Override
    public List<Presence> findAllByChild_GroupChildren_IdOrderByDataTimeEntryDesc(Long id) {
        return null;
    }

    @Override
    public List<Presence> findAllByChild_GroupChildren_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(Long id, LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture) {
        return null;
    }

    @Override
    public List<Presence> findAllByChild_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(Long id, LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture) {
        return this.inMemoryDatabase.values()
                .stream()
                .filter(p -> p.child.getId()== id)
                .filter(p -> p.getDataTimeEntry().isAfter(dataTimeEntry))
                .filter(p -> p .getDataTimeDeparture().isBefore(dataTimeDeparture))
                 .toList();
    }

    @Override
    public List<Presence> findAllByChild_Id(Long id) {
        return null;
    }
}
