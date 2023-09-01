package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.Presence;
import pl.tomwodz.nursery.repository.PresenceDAO;
import pl.tomwodz.nursery.services.PresenceService;

import java.util.List;

@AllArgsConstructor
@Service
public class PresenceServiceImpl implements PresenceService {

    private final PresenceDAO presenceDAO;

    @Override
    public List<Presence> findAll() {
        return this.presenceDAO.findAll();
    }

    @Override
    public Presence findById(Long id) {
        return this.presenceDAO.findById(id);
    }

    @Override
    public Presence save(Presence presence) {
        return this.presenceDAO.save(presence);
    }

    @Override
    public void existsById(Long id) {
        this.presenceDAO.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.presenceDAO.deleteById(id);
    }

    @Override
    public void updateById(Long id, Presence newPresence) {
        this.presenceDAO.updateById(id, newPresence);
    }
}