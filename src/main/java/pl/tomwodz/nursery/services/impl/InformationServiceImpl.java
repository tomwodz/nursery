package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.exception.InformationNotFoundException;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.repository.InformationDAO;
import pl.tomwodz.nursery.services.InformationService;

import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class InformationServiceImpl implements InformationService {

    private final InformationDAO informationDAO;
    @Override
    public List<Information> findAll() {
        return this.informationDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Information findById(Long id) {
        return this.informationDAO.findById(id)
                .orElseThrow(()-> new InformationNotFoundException("Nie znaleziono informacji id: " + id));
    }

    @Override
    public void existsById(Long id) {
        if(!informationDAO.existsById(id)){
            throw new InformationNotFoundException("Nie znaleziono informacji o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.informationDAO.deleteById(id);
    }

    @Override
    public Information save(Information information) {
        return this.informationDAO.save(information);
    }

    @Override
    public void updateById(Long id, Information newInformation) {
        this.existsById(id);
        this.informationDAO.updateById(id, newInformation);
    }
}
