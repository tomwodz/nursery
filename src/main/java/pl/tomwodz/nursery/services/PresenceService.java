package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.Presence;

import java.util.List;

public interface PresenceService {

    List<Presence> findAll();

    Presence findById(Long id);
    Presence save(Presence presence);
    void existsById(Long id);
    void deleteById(Long id);

    void updateById(Long id, Presence newPresence);
}
