package pl.tomwodz.nursery.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.Information;

import java.util.List;
import java.util.Optional;
@Repository
public interface InformationDAO {
    List<Information> findAll(Sort sort);
    Optional<Information> findById(Long id);
    Information save(Information information);
    boolean existsById(Long id);
    void deleteById(Long id);

    void updateById(Long id, Information newInformation);

}
