package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.exception.PresenceNotFoundException;
import pl.tomwodz.nursery.model.Presence;
import pl.tomwodz.nursery.repository.PresenceDAO;
import pl.tomwodz.nursery.services.PresenceService;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class PresenceServiceImpl implements PresenceService {

    private final PresenceDAO presenceDAO;

    @Override
    public List<Presence> findAll() {
        return this.presenceDAO.findAll(Sort.by(Sort.Direction.DESC, "day"));
    }

    @Override
    public List<Presence> findAllByChildIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo) {
        return this.presenceDAO.findAllByChild_IdAndDayBetweenOrderByDayDesc(id, dateFrom, dateTo);
    }

    @Override
    public List<Presence> findAllByGroupChildrenIdAndDayBetween(Long id, LocalDate dateFrom, LocalDate dateTo) {
        return this.presenceDAO.findAllByChild_GroupChildren_IdAndDayBetweenOrderByDayDesc(id, dateFrom, dateTo);
    }

    @Override
    public List<Presence> findAllByChildId(Long id) {
        return this.presenceDAO.findAllByChild_IdOrderByDayDesc(id);
    }

    @Override
    public List<Presence> findAllByGroupChildrenId(Long id) {
        return this.presenceDAO.findAllByChild_GroupChildren_IdOrderByDayDesc(id);
    }

    @Override
    public Presence findById(Long id) {
        return this.presenceDAO.findById(id).
                orElseThrow(()-> new PresenceNotFoundException("Nie znaleziono obecności o id: " + id));
    }

    @Override
    public boolean findFirstByChildId(Long id) {
        if(this.presenceDAO.findFirstByChild_Id(id).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Presence save(Presence presence) {
        return this.presenceDAO.save(presence);
    }

    @Override
    public void existsById(Long id) {
        if(!presenceDAO.existsById(id)){
            throw new PresenceNotFoundException("Nie znaleziono obecności o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.presenceDAO.deleteById(id);
    }

    @Override
    public void updateById(Long id, Presence newPresence) {
        this.existsById(id);
        this.presenceDAO.updateById(id, newPresence);
    }
}
