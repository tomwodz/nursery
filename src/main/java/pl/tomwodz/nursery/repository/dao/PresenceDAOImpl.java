package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.exception.PresenceNotFoundException;
import pl.tomwodz.nursery.model.Presence;
import pl.tomwodz.nursery.repository.PresenceDAO;
import pl.tomwodz.nursery.repository.dao.springdata.PresenceRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class PresenceDAOImpl implements PresenceDAO {

    private final PresenceRepository presenceRepository;

    @Override
    public List<Presence> findAll() {
        return this.presenceRepository.findAll(Sort.by(Sort.Direction.DESC, "day"));
    }

    @Override
    public List<Presence> findAllByChildIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo) {
        return this.presenceRepository.findAllByChild_IdAndDayBetween(id, dateFrom, dateTo);
    }

    @Override
    public List<Presence> findAllByGroupChildrenIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo) {
        return this.presenceRepository.findAllByChild_GroupChildren_IdAndDayBetween(id, dateFrom, dateTo);
    }

    @Override
    public List<Presence> findAllByChildId(Long id) {
        return this.presenceRepository.findAllByChild_Id(id);
    }

    @Override
    public Presence findById(Long id) {
        return this.presenceRepository.findById(id).
                orElseThrow(()-> new PresenceNotFoundException("Nie znaleziono obecności o id: " + id));
    }

    @Override
    public Presence save(Presence presence) {
        return this.presenceRepository.save(presence);
    }

    @Override
    public void existsById(Long id) {
        if(!presenceRepository.existsById(id)){
            throw new PresenceNotFoundException("Nie znaleziono obecności o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.presenceRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, Presence newPresence) {
        this.presenceRepository.updateById(id, newPresence);
    }
}
