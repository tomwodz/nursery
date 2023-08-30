package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.repository.InformationDAO;
import pl.tomwodz.nursery.services.InformationService;

import java.util.List;
@AllArgsConstructor
@Service
public class InformationServiceImpl implements InformationService {

    private final InformationDAO informationDAO;
    @Override
    public List<Information> findAll() {
        return this.informationDAO.findAll();
    }

    @Override
    public Information findById(Long id) {
        return this.informationDAO.findById(id);
    }

    @Override
    public void existsById(Long id) {
        this.informationDAO.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.informationDAO.deleteById(id);
    }

    @Override
    public Information save(Information information) {
        return this.informationDAO.save(information);
    }

    @Override
    public void updateById(Long id, Information newInformation) {
        this.informationDAO.updateById(id, newInformation);
    }
}
