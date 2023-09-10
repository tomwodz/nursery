package pl.tomwodz.nursery.domain.information;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends Repository <Information, Long> {

    List<Information> findAll(Sort sort);

    Optional<Information> findById(Long id);
    Information save(Information information);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);

}
