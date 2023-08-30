package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.exception.InformationNotFoundException;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.repository.InformationDAO;
import pl.tomwodz.nursery.repository.dao.springdata.InformationRepository;

import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class InformationDAOImpl implements InformationDAO {

    private final InformationRepository informationRepository;
    @Override
    public List<Information> findAll() {
        return this.informationRepository.findAll(
                Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Information findById(Long id) {
        return this.informationRepository.findById(id).
                orElseThrow(()-> new InformationNotFoundException("Nie znaleziono informacji id: " + id));
    }

    @Override
    public void existsById(Long id) {
        if(!informationRepository.existsById(id)){
            throw new InformationNotFoundException("Nie znaleziono informacji o id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.existsById(id);
        this.informationRepository.deleteById(id);
    }

    @Override
    public Information save(Information information) {
        return this.informationRepository.save(information);
    }

    @Override
    public void updateById(Long id, Information newInformation) {
        this.informationRepository.updateById(id,newInformation);
    }
}
